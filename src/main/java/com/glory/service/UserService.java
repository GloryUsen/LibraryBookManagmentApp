package com.glory.service;

import java.util.List;

import com.glory.dto.UserRequestDto;
import com.glory.dto.UserResponseDto;
import com.glory.dto.PageUserResponse;


public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);

   // UserDto getUserById(Long id);

    //Optional<User> getByEmail(String email);

    UserResponseDto getByEmail(String email);

    PageUserResponse getAllUsers(int pageNo, int pageSize, String sortBy, String direction);

    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);

    void deleteUser(Long id);
}