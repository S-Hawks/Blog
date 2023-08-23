package dev.faiaz.blog.services.implementation;

import dev.faiaz.blog.entities.Comment;
import dev.faiaz.blog.entities.Post;
import dev.faiaz.blog.exceptions.ResourceNotFoundException;
import dev.faiaz.blog.payloads.CommentDto;
import dev.faiaz.blog.repositories.CommentRepository;
import dev.faiaz.blog.services.CommentService;
import dev.faiaz.blog.services.PostService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostService postService;

    private final CommentRepository commentRepository;

    private final ModelMapper modelMapper;
    @Override
    @Transactional
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = modelMapper.map(postService.getPostById(postId), Post.class);
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment saveComment = commentRepository.save(comment);
        return modelMapper.map(saveComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundException("Comment", "commentId", commentId)
        );
        commentRepository.delete(comment);
    }
}
