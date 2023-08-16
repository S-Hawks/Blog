package dev.faiaz.blog.services;

import dev.faiaz.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
     CategoryDto createCategory(CategoryDto categoryDto);
     CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
     void deleteCategory(Integer categoryId);
     List<CategoryDto> getAllCategory();
     CategoryDto getCategory(Integer categoryId);
}
