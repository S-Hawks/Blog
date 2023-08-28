package dev.faiaz.blog.payloads;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {

    private Integer id;

    @Size(min = 3, message = "content must be in {min} character")
    private String content;

}
