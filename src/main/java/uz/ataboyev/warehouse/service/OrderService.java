package uz.ataboyev.warehouse.service;

import uz.ataboyev.warehouse.payload.*;
import uz.ataboyev.warehouse.payload.clientDtos.ClientOrderDto;

import java.util.List;


public interface OrderService {
    ApiResult<?> byOrder(Long whId, Long categoryId);

    ApiResult<?> addOrder(SaveOrderDTO orderDTO);

    ApiResult<?> addSavdo(SaveOrderDTO orderDTO);

    ApiResult<?> getAllOrder();

    CustomPage<OrderPageDTO> getOrdersPageable(int page, int size,Long warehouseId);

    OrderPriceDto generalPriceOrders(Long whId);

    OneOrderHistoryDto  getOrderItemsOneById(Long orderId);

    OrderPriceDtoForPayTypeRes getPriceAmountByPayType(Long whId);
}
