package uz.ataboyev.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.ataboyev.warehouse.payload.*;
import uz.ataboyev.warehouse.payload.clientDtos.ClientOrderDto;
import uz.ataboyev.warehouse.service.OrderService;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {
    private final OrderService orderService;


    @Override
    public ApiResult<?> byOrder(Long whId, Long categoryId) {
        return orderService.byOrder(whId, categoryId);
    }

    @Override
    public ApiResult<?> addOrder(SaveOrderDTO orderDTO) {
        return orderService.addOrder(orderDTO);
    }

    @Override
    public OneOrderHistoryDto getOrderItemsOneById(Long orderId) {
        return orderService.getOrderItemsOneById(orderId);
    }

    @Override
    public ApiResult<?> getAllOrder() {
        return orderService.getAllOrder();
    }

    @Override
    public CustomPage<OrderPageDTO> getAllPageable(int page, int size,Long whId) {
        return orderService.getOrdersPageable(page, size,whId);
    }

    @Override
    public OrderPriceDto generalPriceOrders(Long whId) {
        return orderService.generalPriceOrders(whId);
    }

    @Override
    public OrderPriceDtoForPayTypeRes getPriceAmountByPayType(Long whId) {
        return orderService.getPriceAmountByPayType(whId);
    }


}
