package com.glory.service;

import java.util.List;
import java.util.Optional;

import com.glory.dto.UserDto;
import com.glory.entity.User;

public interface UserService {

    UserDto createUser(UserDto userDto);

   // UserDto getUserById(Long id);

    //Optional<User> getByEmail(String email);

    UserDto getByEmail(String email);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);
}