package uz.ataboyev.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.ataboyev.warehouse.entity.Order;
import uz.ataboyev.warehouse.entity.OrderItem;
import uz.ataboyev.warehouse.entity.Product;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;
import uz.ataboyev.warehouse.enums.OrderType;
import uz.ataboyev.warehouse.enums.PayTypeEnum;
import uz.ataboyev.warehouse.exception.RestException;
import uz.ataboyev.warehouse.payload.*;
import uz.ataboyev.warehouse.payload.clientDtos.ClientDtoForPageable;
import uz.ataboyev.warehouse.payload.clientDtos.ClientOrderDto;
import uz.ataboyev.warehouse.repository.OrderItemRepository;
import uz.ataboyev.warehouse.repository.OrderRepository;
import uz.ataboyev.warehouse.service.base.BaseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static uz.ataboyev.warehouse.enums.CurrencyTypeEnum.SUM;
import static uz.ataboyev.warehouse.enums.PayTypeEnum.*;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductService productService;
    private final ClientService clientService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final BaseService baseService;

    @Override
    public ApiResult<?> byOrder(Long whId, Long categoryId) {

//        List<OptionResDto> productResDtoList = productService.getProductsForOptionByCategoryId(categoryId);
//        List<OptionResDto> clientResDtoList = clientService.getClientsForOption();
//
//        OrderDTO result = new OrderDTO(
//                OptionDTO.makeOptionDTO(null),
//                OptionDTO.makeOptionDTO(productResDtoList),
//                OptionDTO.makeOptionDTO(clientResDtoList)
//        );
//
//        return ApiResult.successResponse(result);
        return null;
    }

    @Override
    @Transactional
    public ApiResult<?> addOrder(SaveOrderDTO orderDTO) {

        //warehouse id, product id va client idlarni haqiqatdan bazada mavjudligini soradi
        checkingOrderDTO(orderDTO);
        //chiqim bo'lganda bazadan minus qilish uchun kerak bo'ladi
        double d = -1;

        //agar kirim bo'lsa
        if (orderDTO.getOrderType().equals(OrderType.INCOME)){
            d=1;
        }

        //PRODUCTLARNI BAZADAGI SONLARINI O'ZGARTIRIB SAQLAB QO'YDI
        String isGood = editProductCount(orderDTO.getOrderItemDtoList(),d);
        if (!isGood.equals("good")) return ApiResult.errorResponse(isGood);

//        List<OrderItemDto> orderItemDtoList = orderDTO.getOrderItemDtoList();

        Order order = Order.make(orderDTO);
        orderRepository.save(order);

        List<OrderItem> orderItems = makeOIList(orderDTO.getOrderItemDtoList(), order,d);

        //SAVDODAGI BARCHA MAXSULOTLARNI NARHINI YIG'IBERADI SUM VA DOLLARNI ADDENNI QILIB
        OrderPriceDto orderPriceDto = calculationOrderPrice(orderItems);

        saveOrder(order, orderPriceDto);

        orderItemListSaved(orderItems);

        return ApiResult.successResponse("Oldi berdi muvaffaqiyatli saqlandi");
    }


    @Override
    public ApiResult<?> getAllOrder() {
        return null;
    }

    @Override
    public CustomPage<OrderPageDTO> getOrdersPageable(int page, int size, Long warehouseId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedAt"));
        Page<Order> orderPage = orderRepository.findAllByWarehouseIdOrderByUpdatedAtDesc(warehouseId, pageable);
        return orderPageDTOCustomPage(orderPage);
    }

    @Override
    public OrderPriceDto generalPriceOrders(Long whId) {
        double sum, dollar;

        //todo mukammal emas yangicha ajoyib query yozish kerak bo'lyapdi
        OrderPriceDtoForRep orderPriceDtoForRep = orderRepository.orderPriceByWhId(whId);

        dollar = orderPriceDtoForRep.getDollar() == null ? 0D : Double.parseDouble(orderPriceDtoForRep.getDollar());
        sum = orderPriceDtoForRep.getSum() == null ? 0D : Double.parseDouble(orderPriceDtoForRep.getSum());
        return OrderPriceDto.make(sum, dollar);
    }

    @Override
    public OneOrderHistoryDto getOrderItemsOneById(Long orderId) {

        List<ClientOrderDto> listOrderItems = baseService.getOrderItemListByOrderId(orderId);

        Optional<GetDescriptionByOrderId> optionalDescription = orderRepository.getDescriptionById(orderId);
        String description = optionalDescription.isEmpty() ? " " : optionalDescription.get().getDescription();


        return new OneOrderHistoryDto(listOrderItems, description);
    }

    @Override
    public OrderPriceDtoForPayTypeRes getPriceAmountByPayType(Long whId) {
        ArrayList<OrderPriceDtoForPayType> orderPriceByType = new ArrayList<>();
        double allSum = 0D, allDollar = 0D;
        int countCASH = 0, countCARD = 0, countTRANSFER = 0, countDEBT = 0, countBOSS = 0;
        HashMap<PayTypeEnum, HashMap<String, Double>> hashMap = new HashMap<>();

        List<OrderPriceForPayType> allPriceByType = orderItemRepository.getAllPriceByType(whId);


        HashMap<String, Double> mapCASH = new HashMap<>();
        mapCASH.put(CASH.toString(), 0D);
        HashMap<String, Double> mapTRANSFER = new HashMap<>();
        mapCASH.put(TRANSFER.toString(), 0D);
        HashMap<String, Double> mapCARD = new HashMap<>();
        mapCASH.put(CARD.toString(), 0D);
        HashMap<String, Double> mapBOSS = new HashMap<>();
        mapCASH.put(BOSS.toString(), 0D);
        HashMap<String, Double> mapDEBT = new HashMap<>();
        mapCASH.put(DEBT.toString(), 0D);

        for (OrderPriceForPayType priceForPayType : allPriceByType) {
            switch (priceForPayType.getPayType()) {
                case "CASH":
                    mapCASH.put(priceForPayType.getCurrencyType(), Double.parseDouble(priceForPayType.getPrice()));
                    hashMap.put(CASH, mapCASH);

                    //UMUMIY BALANSNI HISOBLASH UCHUN
                    if (priceForPayType.getCurrencyType().equals(SUM.toString()))
                        allSum += Double.parseDouble(priceForPayType.getPrice());
                    else allDollar += Double.parseDouble(priceForPayType.getPrice());
                    countCASH++;
                    break;

                case "CARD":
                    mapCARD.put(priceForPayType.getCurrencyType(), Double.parseDouble(priceForPayType.getPrice()));
                    hashMap.put(PayTypeEnum.CARD, mapCARD);

                    //UMUMIY BALANSNI HISOBLASH UCHUN
                    if (priceForPayType.getCurrencyType().equals(SUM.toString()))
                        allSum += Double.parseDouble(priceForPayType.getPrice());
                    else allDollar += Double.parseDouble(priceForPayType.getPrice());
                    countCARD++;
                    break;
                case "TRANSFER":
                    mapCARD.put(priceForPayType.getCurrencyType(), Double.parseDouble(priceForPayType.getPrice()));
                    hashMap.put(TRANSFER, mapTRANSFER);

                    //UMUMIY BALANSNI HISOBLASH UCHUN
                    if (priceForPayType.getCurrencyType().equals(SUM.toString()))
                        allSum += Double.parseDouble(priceForPayType.getPrice());
                    else allDollar += Double.parseDouble(priceForPayType.getPrice());
                    countTRANSFER++;
                    break;

                case "BOSS":
                    mapBOSS.put(priceForPayType.getCurrencyType(), Double.parseDouble(priceForPayType.getPrice()));
                    hashMap.put(PayTypeEnum.BOSS, mapBOSS);

                    //UMUMIY BALANSNI HISOBLASH UCHUN
                    if (priceForPayType.getCurrencyType().equals(SUM.toString()))
                        allSum += Double.parseDouble(priceForPayType.getPrice());
                    else allDollar += Double.parseDouble(priceForPayType.getPrice());
                    countBOSS++;
                    break;
                case "DEBT":
                    mapDEBT.put(priceForPayType.getCurrencyType(), Double.parseDouble(priceForPayType.getPrice()));
                    hashMap.put(DEBT, mapDEBT);

                    //UMUMIY BALANSNI HISOBLASH UCHUN
                    if (priceForPayType.getCurrencyType().equals(SUM.toString()))
                        allSum += Double.parseDouble(priceForPayType.getPrice());
                    else allDollar += Double.parseDouble(priceForPayType.getPrice());
                    countDEBT++;
                    break;
            }
        }
        //CASH UCHUN
        if (countCASH > 0) orderPriceByType.add(new OrderPriceDtoForPayType(
                CASH,
                hashMap.get(CASH).getOrDefault("SUM", 0D),
                hashMap.get(CASH).getOrDefault("DOLLAR", 0D)
        ));
        if (countCARD > 0) orderPriceByType.add(new OrderPriceDtoForPayType(
                CARD,
                hashMap.get(CARD).getOrDefault("SUM", 0D),
                hashMap.get(CARD).getOrDefault("DOLLAR", 0D)
        ));

        if (countTRANSFER > 0) orderPriceByType.add(new OrderPriceDtoForPayType(
                TRANSFER,
                hashMap.get(TRANSFER).getOrDefault("SUM", 0D),
                hashMap.get(TRANSFER).getOrDefault("DOLLAR", 0D)
        ));
        if (countBOSS > 0) orderPriceByType.add(new OrderPriceDtoForPayType(
                BOSS,
                hashMap.get(BOSS).getOrDefault("SUM", 0D),
                hashMap.get(BOSS).getOrDefault("DOLLAR", 0D)
        ));
        if (countDEBT > 0) orderPriceByType.add(new OrderPriceDtoForPayType(
                DEBT,
                hashMap.get(DEBT).getOrDefault("SUM", 0D),
                hashMap.get(DEBT).getOrDefault("DOLLAR", 0D)
        ));


        return new OrderPriceDtoForPayTypeRes(orderPriceByType, allSum, allDollar);
    }


    //    --------------------------------- HELPER METHOD -----------------------------------------

    //todo ajoyib lekin optimallashtirish garak bo'gan yo'l bazadagi orginal narxni isoplashda garak
    private List<OrderItem> makeOIList(List<OrderItemDto> orderItemDtoList, Order order, double di) {

        ArrayList<OrderItem> orderItems = new ArrayList<>();
        double count = 0d, amount = 0d, helperCount = 0d,

                //bu oldi berdidagi barcha mahsulotlarning umumiy tannarxi
                mainPrise = 0d;

        for (OrderItemDto orderItemDto : orderItemDtoList) {
            orderItemDto.setCount(di*Math.abs(orderItemDto.getCount()));
            count = orderItemDto.getCount();
            helperCount = count;
            amount = orderItemDto.getAmount();

            //YANI MAXSULOT BAZAGA KIRAYOTGAN BO'LSA ASOSIY NARXINI DONA SUMMASINI KELGAN BAXOSIGA KO'PAYTIRISH ORQALI HAL QILIB QO'YA QOLAMIZ
            if (count > 0) {
                mainPrise += amount * count;
            //OMBORDAN MAXSULOT CHIQIB KETAYOTGAN PAYTI BIZ SOTILAYOTGAN NARXI BO'YICHA EMAS BALKI OMBORGA KELGAN VAQTIDAGI SUMMASI BO'YICHA MINUS QILIB QO'YAMIZ FIFO QOIDASI BO'YICHA bu yo'l orqali
            } else {
                while (helperCount <= 0) { //helperCount - qiymatni olganda bu yo'lga kiradi

                    //bu bazadan kelgan order item clientdan kelgani emas ehtiyot bo'l.
                    //buni helper countini minus qiladi
                    OrderItem dtbOrderItem = orderItemRepository.getFIFOOrderItem(orderItemDto.getProductId()).orElseThrow(() -> RestException.restThrow("Omborda mahsulot yetarli emas"));
                    Double oldCount = dtbOrderItem.getHelperCount();
                    double l = oldCount + helperCount;
                    if (l > 0) {//birinchi bazadan gagan orderItemni helperCountini o'zi yetsa ex: l = 8+(-4)=4
                        mainPrise += dtbOrderItem.getAmount() * helperCount;
                        dtbOrderItem.setHelperCount(l);
                        orderItemRepository.save(dtbOrderItem);
                        break;
                    } else { //l<0 ex: 8 + (-10)
                        mainPrise += dtbOrderItem.getAmount() * (-1) * dtbOrderItem.getHelperCount();
                        dtbOrderItem.setHelperCount(0d);
                        orderItemRepository.save(dtbOrderItem);
                        helperCount = l;//8+ -10 = -2
                    }
                }
            }
            orderItems.add(OrderItem.make(orderItemDto, order, mainPrise));
            mainPrise = 0d;
        }
        return orderItems;
    }

    private CustomPage<OrderPageDTO> orderPageDTOCustomPage(Page<Order> orderPage) {

        List<OrderPageDTO> collect = new ArrayList<>();
        List<Order> orderList = orderPage.getContent();
        String date = "";
        //HAR BIR ORDERNI ORDERPAGEGA O'GIRIB COLLECTGA YIG'IBERADI
        for (Order order : orderList) {

            //ORDER ICHIDAN CLIENTNI MALUMOTLARINI DTOGA ORABERADI
            ClientDtoForPageable clientDto = ClientDtoForPageable.make(order.getClient());
            date = baseService.timestampToString_dd_MM_yyyy(order.getUpdatedAt());

            collect.add(new OrderPageDTO(
                    order.getId(),
                    date,
                    clientDto,
                    order.getOrderPriceSum(),
                    order.getOrderPriceDollar()));
        }

        return new CustomPage<>(
                collect,
                orderPage.getNumberOfElements(),// Elementlar
                orderPage.getNumber(), // Current page dagi elementlar soni
                orderPage.getTotalElements(), // Current page number
                orderPage.getTotalPages(), // Barcha elementlar soni
                orderPage.getSize() //Barcha page lar soni
        );
    }

    private String editProductCount(List<OrderItemDto> orderItemList, double d) {

        double databaseCount;
        ArrayList<Product> productList = new ArrayList<>();

        for (OrderItemDto orderItem : orderItemList) {

            Product product = baseService.getProductByIdOrElseThrow(orderItem.getProductId());

            databaseCount = product.getCount() + d*Math.abs(orderItem.getCount());

            //HARIDOR OLMOQCHI BO'LGAN MIQDORDA BAZADA MAHSULOT BORLIGINI TEKSHIRADI
            if (databaseCount < 0)
                return (product.getName() + " mahsulotidan omborda " + product.getCount() + " dona qolgan holos ");

            product.setCount(databaseCount);

            productList.add(product);
        }
        baseService.savedProductList(productList);
        return "good";
    }

    private void checkingOrderDTO(SaveOrderDTO orderDTO) {

        if (!baseService.existsWarehouse(orderDTO.getWarehouseId()))
            throw RestException.restThrow("SAVDONI SAQLAMOQCHI BO'LGAN OMBORXONA TOPILMADI", HttpStatus.NOT_FOUND);

//        List<Long> productIds = orderDTO.getOrderItemDtoList().stream().map(OrderItemDto::getProductId).collect(Collectors.toList());
//        productService.checkingProductByIdListOrElseThrow(productIds);

        Long clientId = orderDTO.getClientId();
        clientService.checkingClientByIdListOrElseThrow(List.of(clientId));

    }

    private void orderItemListSaved(List<OrderItem> orderItemList) {
        try {

            orderItemRepository.saveAll(orderItemList);

        } catch (Exception e) {
            e.printStackTrace();
            throw RestException.restThrow("Order Item no saved");
        }

    }

    private OrderPriceDto calculationOrderPrice(List<OrderItem> orderItemList) {

        Double sum = orderItemList.stream().filter(orderItem ->
                orderItem.getCurrencyType().equals(SUM)).
                mapToDouble(orderItem ->
                        orderItem.getCount() * orderItem.getAmount()).sum();
        Double dollar = orderItemList.stream().filter(orderItem ->
                orderItem.getCurrencyType().equals(CurrencyTypeEnum.DOLLAR)).
                mapToDouble(orderItem ->
                        orderItem.getCount() * orderItem.getAmount()).sum();
        return new OrderPriceDto(sum, dollar);
    }

    private void saveOrder(Order order, OrderPriceDto orderPriceDto) {
        try {
            order.setOrderPriceSum(orderPriceDto.getSum());
            order.setOrderPriceDollar(orderPriceDto.getDollar());
            orderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
            throw RestException.restThrow("Order no saved");
        }
    }

    private Order saveOrder(Order order) {
        try {

            return orderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
            throw RestException.restThrow("Order no saved");
        }
    }


}
