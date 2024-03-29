package uz.ataboyev.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.component.DataLoader;
import uz.ataboyev.warehouse.entity.Client;
import uz.ataboyev.warehouse.entity.Order;
import uz.ataboyev.warehouse.entity.OrderItem;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;
import uz.ataboyev.warehouse.enums.OrderType;
import uz.ataboyev.warehouse.enums.Type;
import uz.ataboyev.warehouse.exception.RestException;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.ClientItemsForHistory;
import uz.ataboyev.warehouse.payload.DollarAndSum;
import uz.ataboyev.warehouse.payload.OptionResDto;
import uz.ataboyev.warehouse.payload.clientDtos.*;
import uz.ataboyev.warehouse.repository.ClientRepository;
import uz.ataboyev.warehouse.repository.OrderItemRepository;
import uz.ataboyev.warehouse.repository.OrderRepository;
import uz.ataboyev.warehouse.service.base.BaseService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final BaseService baseService;

    @Override
    public ApiResult<?> add(ClientReqDto clientReqDto) {

        checkingClientByPhoneNumberOrElseThrow(clientReqDto.getPhoneNumber(), clientReqDto.getName());

        Client client = Client.make(clientReqDto);

        saveClient(client);
//        saveDefaultOrder(client);
        ClientResDto clientResDto = mapClient(client);

        return ApiResult.successResponse(clientResDto, "success aded");
    }

    private void saveDefaultOrder(Client client) {
        try {
            Order defaultOrder = orderRepository.save(new Order(OrderType.DEFAULT, client.getId(), "default", DataLoader.wh1.getId()));

//            orderItemRepository.save(new OrderItem(defaultOrder.getId(),))

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public ApiResult<?> edit(Long clientId, ClientReqDto clientReqDto) {

        //CLIENTNI BAZADAN OLIB KELADI TOPOLMASA THROW
        Client client = baseService.getClientById(clientId);

        //YANGI TEL RAQAM BAZADA MAVJUD BO'LSA THROW
        if (clientRepository.existsByPhoneNumberAndIdNot(clientReqDto.getPhoneNumber(), client.getId()))
            throw new RestException("Yangi kiritgan raqamiz bazada mavjudligi uchun boshqa raqam kiriting!", HttpStatus.CONFLICT);

        Client saveClient = Client.updateClient(client, clientReqDto);

        saveClient(saveClient);

        ClientResDto clientResDto = mapClient(client);

        return ApiResult.successResponse(clientResDto);
    }

    @Override
    public ApiResult<?> getOne(Long clientId) {

        Client client = baseService.getClientById(clientId);

        ClientResDto clientResDto = mapClient(client);

        return ApiResult.successResponse(clientResDto);
    }

    @Override
    public List<ClientResDto> getAllClient() {
        List<Client> clientList = clientRepository.findAllByClientTypeNot(Type.OTHER);
        return mapClients(clientList);
    }

    @Override
    public List<ClientResDto> getAllWorkers() {
        List<Client> allWorkers = clientRepository.getAllWorkers();
        return mapClients(allWorkers);
    }

    @Override
    public List<ClientResDto> getOrder(Long whId) {
        return mapClients(clientRepository.getOrder(whId));
    }

    @Override
    public OptionResDto getSavdo(Long whId) {
        return  OptionResDto.make(clientRepository.getSavdo(whId));
    }

    @Override
    public List<OptionResDto> getClientsForOption() {
        List<Client> clientList = clientRepository.findAll();
        return clientList.stream().map(OptionResDto::make).collect(Collectors.toList());
    }

    @Override
    public ClientHistoryDto clientHistory(Long clientId) {
        try {
            List<OrderItem> clientItems = orderItemRepository.findAllByOrder_ClientId(clientId);
            return mapClientHistoryDto(clientItems);
        }catch (Exception e){
            return new ClientHistoryDto();
        }
    }

    @Override
    public ClientHistoryDto getSavdoHistory(Long startDate, Long endDate, Long whId) {
        try {
            List<ClientItemsForHistory> getItems = orderItemRepository.getSavdoHistory(new Timestamp(startDate), new Timestamp(endDate), whId);
            List<ClientOrderDto> collect = getItems.stream().map(ClientOrderDto::make2).collect(Collectors.toList());
            DollarAndSum total = orderItemRepository.getTotalSavdoHistory(new Timestamp(startDate), new Timestamp(endDate), whId);
            System.out.println(collect.toString());
            return new ClientHistoryDto(
                    collect,
                    Double.parseDouble(total.getTotalSum()),
                    Double.parseDouble(total.getTotalDollar())
            );

        }catch (Exception e){
            return new ClientHistoryDto();
        }
    }

    @Override
    public List<ClientBalanceResDto> getClientsBalance(Long warehouseId) {
        List<ClientBalance> allClientBalance = clientRepository.getALLClientBalance(warehouseId);
        return ClientBalanceResDto.makeList(allClientBalance);

    }


    @Override
    public ApiResult<?> delete(Long clientId) {

        //MIJOZNING ORDERLARI BOR BOLSA OCHIRMAYMIZ
        if (orderRepository.existsByClientId(clientId)){
            return ApiResult.errorResponse("Bu mijozni o'chira olmaysiz, uning oldi berdilari bo'lgan ekan siz bn");
        }


        if (!baseService.existsClientById(clientId)) {
            return ApiResult.errorResponse("o'chirmoqchi bo'lgan mijozis bazada mavjudmas");
        }

        clientRepository.deleteById(clientId);
        return ApiResult.successResponse("Mijoz o'chirildi");
    }

    @Override
    public List<ClientResDto> getAll() {
        List<Client> clientList = clientRepository.findAll();
        return mapClients(clientList);
    }


    @Override
    public void checkingClientByIdListOrElseThrow(List<Long> clientIdList) {
        for (Long id : clientIdList) {
            if (!clientRepository.existsById(id))
                throw new RestException("Bu mijozni biz bazadan topa olmadik", HttpStatus.NOT_FOUND);
        }
    }
//-----------------------------HELPER METHOD-------------------------------------

    private ClientHistoryDto mapClientHistoryDto(List<OrderItem> clientItems) {
        Double sum = 0D;
        Double dollar = 0D;
        List<ClientOrderDto> list = new ArrayList<>();
        for (OrderItem clientItem : clientItems) {
            ClientOrderDto clientOrderDto = mapClientOrderDto(clientItem);

            if (clientItem.getCurrencyType().equals(CurrencyTypeEnum.SUM))
                sum += clientOrderDto.getPrice() != null ? clientOrderDto.getPrice() : 0D;
            else dollar += clientOrderDto.getPrice() != null ? clientOrderDto.getPrice() : 0D;

            list.add(clientOrderDto);
        }

        return new ClientHistoryDto(list, sum, dollar);
    }

    private ClientOrderDto mapClientOrderDto(OrderItem orderItem) {

        String dateFormat = baseService.timestampToString_dd_MM_yyyy(orderItem.getUpdatedAt());

        return new ClientOrderDto(
                dateFormat,
                orderItem.getProduct().getName(),
                orderItem.getCount(),
                orderItem.getAmount(),
                orderItem.getCurrencyType(),
                orderItem.getAmount() * orderItem.getCount(),
                orderItem.getPayTypeEnum()
        );
    }

    private void checkingClientByPhoneNumberOrElseThrow(String phoneNumber, String name) {
        if (clientRepository.existsByPhoneNumberAndName(phoneNumber, name))
            throw RestException.restThrow("Bu raqamli mijoz bazada mavjud", HttpStatus.CONFLICT);
    }

    private void saveClient(Client client) {
        try {
            clientRepository.save(client);
        } catch (Exception e) {
            e.printStackTrace();
            throw RestException.restThrow("Client not saved");
        }
    }


    //CLIENTDAN DTO YASABERADI
    private ClientResDto mapClient(Client client) {
        return ClientResDto.make(client);
    }

    private List<ClientResDto> mapClients(List<Client> clients) {
        return clients.stream().map(ClientResDto::make).collect(Collectors.toList());

    }


}
