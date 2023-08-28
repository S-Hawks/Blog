package dev.faiaz.blog.payloads;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.faiaz.blog.entities.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;
import org.hibernate.annotations.LazyGroup;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    private Set<CommentDto> comments = new HashSet<>();

}