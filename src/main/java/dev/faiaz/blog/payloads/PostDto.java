package dev.faiaz.blog.payloads;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties({"category", "user"})
public class PostDto {
    private String id;

    @NotNull(message = "Title must not be null")
    private String title;

    @Size(min=10, max=100, message = "Content must be between {min} and {max} character")
    private String content;

    private String imageName;

    private Date date;

    private CategoryDto category;

    private UserDto user;

}