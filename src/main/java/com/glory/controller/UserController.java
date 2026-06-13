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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glory.dto.UserRequestDto;
import com.glory.dto.UserResponseDto;
import com.glory.dto.PageUserResponse;
import com.glory.service.UserService;
import com.glory.utils.AppConstants;

@RestController
@RequestMapping("/api/users")


public class UserController {

    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto){
        return new ResponseEntity<>(userService.createUser(userRequestDto), HttpStatus.CREATED);
    }

     @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> getByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.getByEmail(email));
    }

     @GetMapping
    public ResponseEntity<PageUserResponse> getAllUsers(
        @RequestParam(value = "PageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
        @RequestParam(value = "PageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_PAGE_SORT_BY) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_PAGE_DIRECTION) String direction
    ){
        return ResponseEntity.ok(userService.getAllUsers(pageNo, pageSize, sortBy, direction));
    }

     @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id,
                                               @RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(userService.updateUser(id, userRequestDto));
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
