package dev.faiaz.blog.controllers;

import dev.faiaz.blog.payloads.PostDto;
import dev.faiaz.blog.payloads.UserDto;
import dev.faiaz.blog.services.PostService;
import dev.faiaz.blog.utils.PostEndPointUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @PostMapping(value = PostEndPointUtils.ADD_NEW_POSTS, produces = "application/json")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId
    ){
        PostDto createPost = postService.createPost(postDto, categoryId, userId);
        return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }

    @GetMapping(value = PostEndPointUtils.GET_POST_BY_CATEGORY)
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
        List<PostDto> getByCategory = postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(getByCategory, HttpStatus.FOUND);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
        List<PostDto> getByUser = postService.getPostByUser(userId);
        return new ResponseEntity<>(getByUser,HttpStatus.FOUND);
    }
}
