package dev.faiaz.blog.services.implementation;
import dev.faiaz.blog.entities.Category;
import dev.faiaz.blog.entities.Post;
import dev.faiaz.blog.entities.User;
import dev.faiaz.blog.exceptions.ResourceNotFoundException;
import dev.faiaz.blog.payloads.PostDto;
import dev.faiaz.blog.repositories.CategoryRepository;
import dev.faiaz.blog.repositories.PostRepository;
import dev.faiaz.blog.repositories.UserRepository;
import dev.faiaz.blog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User", "User Id", userId)
        );

        Category category = categoryRepository.findById(categoryId).orElseThrow(
                ()-> new ResourceNotFoundException("Category", "Category Id", categoryId)
        );

        Post post = modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post createPost = postRepository.save(post);
        return modelMapper.map(createPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<PostDto> getAllPost() {
        return null;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        return null;
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
    public List<PostDto> searchPost(String keyWord) {
        return null;
    }

}
