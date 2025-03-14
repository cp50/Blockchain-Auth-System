package com.projectjava.demo.controller;

import com.projectjava.demo.model.User;
import com.projectjava.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ✅ API for User Registration
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String response = userService.registerUser(user);
        return ResponseEntity.ok(response);
    }

    // ✅ API for User Login
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        String token = userService.loginUser(user);
        return ResponseEntity.ok(token);
    }
}
