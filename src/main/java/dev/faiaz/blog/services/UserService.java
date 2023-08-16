package dev.faiaz.blog.services;

import dev.faiaz.blog.entities.User;
import dev.faiaz.blog.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer id);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUser();
    void deleteUser(Integer userId);

}
