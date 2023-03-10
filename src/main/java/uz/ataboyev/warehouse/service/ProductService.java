package uz.ataboyev.warehouse.service;

import uz.ataboyev.warehouse.payload.*;

import java.util.List;

public interface ProductService {
    ApiResult<?> add(ProductReqDto productReqDto);

    ApiResult<?> getOne(Long productId);

    List<ProductResDto> getAllProductsByPCId(Long pCId);

    List<OptionResDto> getProductsForOptionByWhId(Long whId);

    ApiResult<?> edit(Long productId, ProductReqDto productReqDto);

    ApiResult<?> delete(Long productId);


    void checkingProductByIdListOrElseThrow(List<Long> productIdList);

    List<ProductResDtoByWhId> getAllProductByWarehouseId(Long warehouseId);

    List<ProductResDtoByWhId> littleProductsByWarehouseId(Long whId);
}
