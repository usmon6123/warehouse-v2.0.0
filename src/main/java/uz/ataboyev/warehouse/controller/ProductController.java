package uz.ataboyev.warehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.ataboyev.warehouse.payload.*;
import uz.ataboyev.warehouse.utils.RestConstant;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(path = RestConstant.PRODUCT_CONTROLLER)
public interface ProductController {

    @PostMapping("/add")
    ApiResult<?> add(@RequestBody @Valid ProductReqDto productReqDto);

    @GetMapping("/get-one/{productId}")
    ApiResult<?> getOne(@PathVariable Long productId);

    @GetMapping("/get-all-product-by-categoryId/{categoryId}")
    List<ProductResDto> getAllProductByCategoryId(@PathVariable Long categoryId);

    @GetMapping("/get-all-product-by-warehouse-id/{warehouseId}")
    List<ProductResDtoByWhId> getAllProductByWarehouseId(@PathVariable Long warehouseId);


    @GetMapping("/get-products-for-option-by-categoryId/{categoryId}")
    List<OptionResDto> getProductsForOption(@PathVariable Long categoryId);

    @GetMapping("get-codes-by-product-name/{productName}")
    List<GetCodesForProductDto>getCodesForProduct(@PathVariable String productName);

    //MIN COUNTIDAN KAM QOLGAN MAXSULOTLAR RO'YHATINI CHIQARADI
    @GetMapping("get-little-products/{whId}")
    List<ProductResDtoByWhId> littleProductsByWarehouseId(@PathVariable Long whId);

    @PutMapping("/edit/{productId}")
    ApiResult<?> edit(@PathVariable Long productId, @RequestBody @Valid ProductReqDto productReqDto);

    @DeleteMapping("/delete/{productId}")
    ApiResult<?> delete(@PathVariable Long productId);


}
