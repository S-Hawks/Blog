package dev.faiaz.blog.services.implementation;
import dev.faiaz.blog.entities.Category;
import dev.faiaz.blog.entities.Post;
import dev.faiaz.blog.entities.User;
import dev.faiaz.blog.exceptions.ResourceNotFoundException;
import dev.faiaz.blog.payloads.CategoryDto;
import dev.faiaz.blog.payloads.PostDto;
import dev.faiaz.blog.payloads.UserDto;
import dev.faiaz.blog.repositories.CategoryRepository;
import dev.faiaz.blog.repositories.PostRepository;
import dev.faiaz.blog.repositories.UserRepository;
import dev.faiaz.blog.services.CategoryService;
import dev.faiaz.blog.services.PostService;
import dev.faiaz.blog.services.UserService;
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
    private final UserService userService;
    private final CategoryService categoryService;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = getUser(userId);
        Category category = getCategory(categoryId);

        Post post = modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post createPost = postRepository.save(post);
        return modelMapper.map(createPost, PostDto.class);
    }
    //Getting user to create post
    private User getUser(Integer userId){
        UserDto userDto = userService.getUserById(userId);
        return modelMapper.map(userDto, User.class);
    }
    //Getting category for creating post
    private Category getCategory(Integer categoryId){
       CategoryDto categoryDto = categoryService.getCategory(categoryId);
       return modelMapper.map(categoryDto,Category.class);
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
