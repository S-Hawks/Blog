package dev.faiaz.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class PostDto {
    private Integer id;

    @NotEmpty(message = "Title must be not blank")
    private String title;

    @Size(max = 100, message = "Content cannot exceed {max} character")
    private String content;

    private String imageName;

    private Date date;

    private CategoryDto category;

    private UserDto user;

}
