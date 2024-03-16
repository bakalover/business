package com.example.blps2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blps2.repo.request.UserBody;
import com.example.blps2.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserBody user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok().body("OK");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Cannot register user!");
        }

    }

    
    @PostMapping("/login")
    public void login(@RequestBody UserBody user) {
        // Future security
    }

}