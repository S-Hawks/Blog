package dev.faiaz.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {
    private Integer categoryId;

    @NotNull(message = "Category title must not be null")
    private String categoryTitle;

    @Size(max=100, message = "Category description must be between {max} character")
    private String categoryDescription;
}
