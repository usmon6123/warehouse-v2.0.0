package uz.ataboyev.warehouse.service;

import uz.ataboyev.warehouse.payload.*;

import java.util.List;

public interface ProductService {
    ApiResult<?> add(ProductReqDto productReqDto);

    ApiResult<?> getOne(Long productId);

    List<ProductResDto> getAllProductsByCategoryId(Long categoryId);

    List<OptionResDto> getProductsForOptionByCategoryId(Long categoryId);

    ApiResult<?> edit(Long productId, ProductReqDto productReqDto);

    ApiResult<?> delete(Long productId);


    void checkingProductByIdListOrElseThrow(List<Long> productIdList);

    List<ProductResDtoByWhId> getAllProductByWarehouseId(Long warehouseId);

    List<GetCodesForProductDto> getCodesForProduct(String productName);

    List<ProductResDtoByWhId> littleProductsByWarehouseId(Long whId);
}
