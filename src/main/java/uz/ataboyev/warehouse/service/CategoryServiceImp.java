package uz.ataboyev.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.entity.Category;
import uz.ataboyev.warehouse.exception.RestException;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.CategoryDto;
import uz.ataboyev.warehouse.payload.CategoryResDto;
import uz.ataboyev.warehouse.payload.OptionResDto;
import uz.ataboyev.warehouse.repository.CategoryRepository;
import uz.ataboyev.warehouse.repository.ProductRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public ApiResult<?> add(CategoryDto categoryDto) {

        if (categoryRepository.existsByNameAndWarehouseId(categoryDto.getName(), categoryDto.getWarehouseId()))
            throw RestException.exception("Bu catalog bazada mavjud", HttpStatus.CONFLICT);
        Category category = new Category(categoryDto.getName(), categoryDto.getWarehouseId());

        categoryRepository.save(category);

        return ApiResult.successResponse("SUCCESS ADDED id: " + category.getId() + " name: " + category.getName());
    }

    @Override
    public ApiResult<?> getOne(Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RestException("Category not Found", HttpStatus.NOT_FOUND));

        CategoryResDto categoryResDto =
                CategoryResDto.make(category);

        return ApiResult.successResponse(categoryResDto);
    }


    @Override
    public ApiResult<?> getAllCategories(Long wareHouseId) {
        List<Category> categories = getCategoriesByWhId(wareHouseId);
        List<CategoryResDto> collect = categories.stream().map(CategoryResDto::make).collect(Collectors.toList());
        return ApiResult.successResponse(collect);
    }

    @Override
    public List<OptionResDto> getCategoryListForOption(Long wareHouseId) {
        List<Category> categories = categoryRepository.findAllByWarehouseId(wareHouseId);
        return categories.stream().map(OptionResDto::make).collect(Collectors.toList());
    }

    @Override
    public ApiResult<?> edit(Long categoryId, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> RestException.restThrow("Bu idli Categoriya mavjudmas", HttpStatus.NOT_FOUND));
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return ApiResult.successResponse("SUCCESS_EDITED");

    }

    @Override
    public ApiResult<?> delete(Long categoryId) {
        if (productRepository.existsByCategoryId(categoryId))
            throw RestException.restThrow("Bu categoriyani o'chira olmaysiz, ichida productlar mavjud", HttpStatus.CONFLICT);
        if (!categoryRepository.existsById(categoryId))
            throw RestException.restThrow("Bu idli Categoriya mavjudmas", HttpStatus.NOT_FOUND);

        categoryRepository.deleteById(categoryId);

        return ApiResult.successResponse("DELETED_CATEGORY");
    }

    private List<Category> getCategoriesByWhId(Long wareHouseId) {
        return  categoryRepository.findAllByWarehouseId(wareHouseId);

    }



}
