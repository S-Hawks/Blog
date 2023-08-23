package dev.faiaz.blog.controllers;

import dev.faiaz.blog.payloads.ApiResponse;
import dev.faiaz.blog.payloads.CommentDto;
import dev.faiaz.blog.services.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController{
    private  final CommentService commentService;

    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentDto> createComment(
            @Valid
            @RequestBody CommentDto commentDto,
            @PathVariable Integer postId
    ){
        return new ResponseEntity<>(commentService.createComment(commentDto, postId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer id){
        commentService.deleteComment(id);
        return ResponseEntity.ok(new ApiResponse("Comment Deleted successfully", true));
    }

}
