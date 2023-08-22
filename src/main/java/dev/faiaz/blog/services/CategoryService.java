package dev.faiaz.blog.services;

import dev.faiaz.blog.payloads.CategoryDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
     CategoryDto createCategory(CategoryDto categoryDto);
     CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
     void deleteCategory(Integer categoryId);
     Page<CategoryDto> getAllCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
     CategoryDto getCategory(Integer categoryId);
}
