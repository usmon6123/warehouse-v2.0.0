package uz.ataboyev.warehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.ataboyev.warehouse.payload.*;
import uz.ataboyev.warehouse.payload.clientDtos.ClientOrderDto;
import uz.ataboyev.warehouse.utils.RestConstant;

import javax.validation.Valid;
import java.util.List;

import static uz.ataboyev.warehouse.utils.AppConstant.DEFAULT_PAGE_NUMBER;
import static uz.ataboyev.warehouse.utils.AppConstant.DEFAULT_PAGE_SIZE;

@RequestMapping(path = OrderController.ORDER_CONTROLLER)
public interface OrderController {

    String ORDER_CONTROLLER = RestConstant.BASE_PATH + "/order";

    //BU YO'L ISHLATILMADI
    @GetMapping("/by-order/{whId}/{categoryId}")
    ApiResult<?> byOrder(@PathVariable Long whId, @PathVariable Long categoryId);


    @PostMapping("/add-order")
    ApiResult<?> addOrder(@RequestBody @Valid SaveOrderDTO orderDTO);

    @GetMapping("/get-one/{orderId}")
    OneOrderHistoryDto getOrderItemsOneById(@PathVariable Long orderId);

    @GetMapping("/get-all-order")
    ApiResult<?> getAllOrder();//BU YO'L YOZILMAGAN

    @GetMapping("get-all-orders-pageable/{whId}")
    CustomPage<OrderPageDTO> getAllPageable(@RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                                  @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int size,
                                                  @PathVariable Long whId);

    @GetMapping("general-price-orders/{whId}")
    OrderPriceDto generalPriceOrders(@PathVariable Long whId);

    @GetMapping("/get-price-pay-type/{whId}")
    OrderPriceDtoForPayTypeRes getPriceAmountByPayType(@PathVariable Long whId);

}
