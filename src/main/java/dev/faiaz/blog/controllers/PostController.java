package dev.faiaz.blog.controllers;

import dev.faiaz.blog.payloads.ApiResponse;
import dev.faiaz.blog.payloads.PostDto;
import dev.faiaz.blog.payloads.PostResponse;
import dev.faiaz.blog.services.PostService;
import dev.faiaz.blog.utils.PostEndPointUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @PostMapping(value = PostEndPointUtils.ADD_NEW_POSTS, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDto> createPost(
            @Valid
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

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer id){
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "1", required = false ) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
            ){
        return new ResponseEntity<>(postService.getAllPost(pageNumber, pageSize, sortBy, sortDir),HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer id){
        return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer id){
        postService.deletePost(id);
        return ResponseEntity.ok(new ApiResponse("Post Deleted Successfully", true));
    }

    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchByKeyword(@PathVariable String keywords){
        return new ResponseEntity<>(postService.searchPost(keywords), HttpStatus.OK);
    }

}
