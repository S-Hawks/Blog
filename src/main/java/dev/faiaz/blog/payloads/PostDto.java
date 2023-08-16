package dev.faiaz.blog.payloads;

import lombok.Data;

import java.util.Date;

@Data
public class PostDto {
    private String title;

    private String content;

    private String imageName;

    private Date date;

    private CategoryDto category;

    private UserDto user;

}
