package com.glory.service;

import java.util.List;

import com.glory.dto.UserDto;


public interface UserService {

    UserDto createUser(UserDto userDto);

   // UserDto getUserById(Long id);

    //Optional<User> getByEmail(String email);

    UserDto getByEmail(String email);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);
}