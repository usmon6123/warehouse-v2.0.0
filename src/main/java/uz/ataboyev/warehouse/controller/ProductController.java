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

    @GetMapping("/get-all-product-by-pCId/{pCId}")
    List<ProductResDto> getAllProductByPCId(@PathVariable Long pCId);

    @GetMapping("/get-all-product-by-warehouse-id/{warehouseId}")
    List<ProductResDtoByWhId> getAllProductByWarehouseId(@PathVariable Long warehouseId);

    //MAHSULOT FIRMASINING ID SI BERILGANDA O'SHA FIRMANING BARCHA MAXSULOTLARI NAME VA ID SINI OLIB KELADI
    @GetMapping("/get-products-for-option-by-company-id/{pCId}")
    List<OptionResDto> getProductsForOption(@PathVariable Long pCId);

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
