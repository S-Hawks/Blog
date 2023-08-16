package dev.faiaz.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {
    private Integer categoryId;

    @NotEmpty(message = "Category title most not be empty")
    @Size(max = 50, message = "Category title cannot exceed {max} character")
    private String categoryTitle;

    @NotEmpty
    @Size(min = 10)
    private String categoryDescription;
}
