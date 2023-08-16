package dev.faiaz.blog.services;



import dev.faiaz.blog.payloads.PostDto;

import java.util.List;

public interface PostService {
    dev.faiaz.blog.payloads.PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);
    dev.faiaz.blog.payloads.PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    List<PostDto> getAllPost();

    PostDto getPostById(Integer postId);

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPost(String keyWord);
}
