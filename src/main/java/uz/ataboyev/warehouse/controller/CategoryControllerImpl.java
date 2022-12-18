package uz.ataboyev.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.CategoryDto;
import uz.ataboyev.warehouse.payload.OptionResDto;
import uz.ataboyev.warehouse.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;

    @Override
    public ApiResult<?> add(@Valid CategoryDto categoryDto) {

        return categoryService.add(categoryDto);
    }

    @Override
    public ApiResult<?> getOne(Long categoryId) {

        return categoryService.getOne(categoryId);
    }

    @Override
    public ApiResult<?> getAllCategories(Long wareHouseId) {

        return categoryService.getAllCategories(wareHouseId);
    }

    @Override
    public List<OptionResDto> getAllCategoriesForOption(Long wareHouseId) {
        return categoryService.getCategoryListForOption(wareHouseId);
    }


    @Override
    public ApiResult<?> edit(Long categoryId, CategoryDto categoryDto) {
        return categoryService.edit(categoryId, categoryDto);
    }

    @Override
    public ApiResult<?> delete(Long categoryId) {

        return categoryService.delete(categoryId);
    }


}
