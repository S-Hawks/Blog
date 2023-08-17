package dev.faiaz.blog.services.implementation;
import dev.faiaz.blog.entities.Category;
import dev.faiaz.blog.entities.Post;
import dev.faiaz.blog.entities.User;
import dev.faiaz.blog.exceptions.ResourceNotFoundException;
import dev.faiaz.blog.payloads.PostDto;
import dev.faiaz.blog.repositories.PostRepository;
import dev.faiaz.blog.services.CategoryService;
import dev.faiaz.blog.services.PostService;
import dev.faiaz.blog.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CategoryService categoryService;

    @Override
    @Transactional
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = modelMapper.map(userService.getUserById(userId), User.class);

        Category category = modelMapper.map(categoryService.getCategory(categoryId), Category.class);

        Post post = modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post createPost = postRepository.save(post);
        return modelMapper.map(createPost, PostDto.class);
    }
    @Override
    @Transactional
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post", "PostId", postId)
        );
        BeanUtils.copyProperties(postDto, post, "id");
        Post updatePost = postRepository.save(post);
        return modelMapper.map(updatePost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post", "PostId", postId)
        );
        postRepository.delete(post);
    }

    @Override
    public Page<PostDto> getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        //Implement Sorting
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        //Implement Page to fetch certain amount of data
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return postRepository.findAll(pageable)
                .map(post -> modelMapper.map(post, PostDto.class));
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "PostId", postId)
        );
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        List<Post> posts = postRepository.findByCategoryId(categoryId);
        List<PostDto> postDtos = posts.stream().map((cat) -> modelMapper.map(cat, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        List<PostDto> postDtos = posts.stream().map((post -> modelMapper.map(post,PostDto.class))).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        List<Post> posts = postRepository.searchByKeyword(keyword);
        List<PostDto> postDtos = posts.stream().map((p) -> modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

}
