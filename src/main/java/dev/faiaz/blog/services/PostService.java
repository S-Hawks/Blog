package dev.faiaz.blog.services;



import dev.faiaz.blog.payloads.PostDto;
import dev.faiaz.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {
    dev.faiaz.blog.payloads.PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);
    dev.faiaz.blog.payloads.PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPost(String keyWord);
}
