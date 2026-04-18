package com.glory.serviceImpl;

import org.springframework.stereotype.Service;

import com.glory.repository.UserRepository;
import com.glory.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }



}
