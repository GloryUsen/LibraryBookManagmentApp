package com.glory.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glory.dto.UserDto;
import com.glory.service.UserService;

@RestController
@RequestMapping("/api/users")


public class UserController {

    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

     @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.getByEmail(email));
    }

     @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

     @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,
                                               @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    // @GetMapping("/test")
    // public String test(){
    //         return "API WORKS";
    //     }

        @PostMapping("/debug")
    public String debug() {
        System.out.println("CONTROLLER HIT");
        return "OK";
    }

}
