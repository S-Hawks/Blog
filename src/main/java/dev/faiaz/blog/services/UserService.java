package dev.faiaz.blog.services;

import dev.faiaz.blog.entities.User;
import dev.faiaz.blog.payloads.UserDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer id);
    UserDto getUserById(Integer userId);
    Page<UserDto> getAllUser(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    void deleteUser(Integer userId);

}
