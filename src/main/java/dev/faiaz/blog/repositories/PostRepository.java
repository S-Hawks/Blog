package dev.faiaz.blog.repositories;

import dev.faiaz.blog.entities.Category;
import dev.faiaz.blog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByCategoryId(Integer categoryId);
    List<Post> findByUserId(Integer userId);
}
