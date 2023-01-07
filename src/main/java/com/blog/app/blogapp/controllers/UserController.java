package com.blog.app.blogapp.controllers;

import com.blog.app.blogapp.payloads.ApiResponse;
import com.blog.app.blogapp.payloads.UserDto;
import com.blog.app.blogapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    //POST Create User
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUserDto=this.userService.createUser(userDto);

        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    //PUT Update User

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto , @PathVariable("userId") Integer userId){

        UserDto updatedUser=this.userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updatedUser);

    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse>  deleteUser(@PathVariable("userId") Integer uid){

        this.userService.deleteUser(uid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
    }
    //GET User

    @GetMapping("/")
    public  ResponseEntity<List<UserDto>> getAllUsers(){


        return ResponseEntity.ok(this.userService.getAllUsers());
    }
    //GET Single User
    @GetMapping("/{UserId}")
    public  ResponseEntity<UserDto> getSingleUsers(@PathVariable Integer UserId){


        return ResponseEntity.ok(this.userService.getUserById(UserId));
    }
}
