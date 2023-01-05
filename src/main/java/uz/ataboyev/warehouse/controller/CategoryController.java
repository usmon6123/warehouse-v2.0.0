//package uz.ataboyev.warehouse.controller;
//
//import org.springframework.web.bind.annotation.*;
//import uz.ataboyev.warehouse.payload.ApiResult;
//import uz.ataboyev.warehouse.payload.CategoryDto;
//import uz.ataboyev.warehouse.payload.OptionResDto;
//import uz.ataboyev.warehouse.utils.RestConstant;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RequestMapping(path = RestConstant.CATEGORY_CONTROLLER)
//public interface CategoryController {
//
//    @PostMapping("/add")
//    ApiResult<?> add(@RequestBody @Valid CategoryDto categoryDto);
//
//    @GetMapping("/get-one/{categoryId}")
//    ApiResult<?> getOne(@PathVariable Long categoryId);
//
//    @GetMapping("/get-all-categories/{wareHouseId}")
//    ApiResult<?> getAllCategories(@PathVariable Long wareHouseId);
//
//    @GetMapping("/get-categories-for-option/{wareHouseId}")
//    List<OptionResDto> getAllCategoriesForOption(@PathVariable Long wareHouseId);
//
//
//    @PutMapping("/edit/{categoryId}")
//    ApiResult<?> edit(@PathVariable Long categoryId, @RequestBody CategoryDto categoryDto);
//
//    @DeleteMapping("/delete/{categoryId}")
//    ApiResult<?> delete(@PathVariable Long categoryId);
//
//}
