package dev.faiaz.blog.payloads;

import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    private List<PostDto> content;

    private Integer pageNumber;

    private Integer pageSize;

    private Long totalElement;

    private Integer totalPages;

    private boolean lastPage;
}
