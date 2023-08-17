package dev.faiaz.blog.services.implementation;

import com.fasterxml.jackson.databind.util.BeanUtil;
import dev.faiaz.blog.entities.Category;
import dev.faiaz.blog.exceptions.ResourceNotFoundException;
import dev.faiaz.blog.payloads.CategoryDto;
import dev.faiaz.blog.repositories.CategoryRepository;
import dev.faiaz.blog.services.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    @Override
    @Transactional
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryRepository.save(category);
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    @Transactional
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", "Id", categoryId)
        );
//        category.setCategoryTitle(categoryDto.getCategoryTitle());
//        category.setCategoryDescription(categoryDto.getCategoryDescription());
        BeanUtils.copyProperties(categoryDto,category,"id");
        Category updatedCategory = categoryRepository.save(category);
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", "Id", categoryId)
        );
        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map((cat) -> modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", "Id", categoryId)
        );
        return modelMapper.map(category, CategoryDto.class);
    }
}
