package com.example.blps2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blps2.repo.request.UserBody;
import com.example.blps2.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Base64;

@Profile("sec")
@RestController
@RequestMapping("/user")
public class UserController {

    private final String okMsg = "Ok\n";

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserBody user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok().body(okMsg);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Cannot register user!");
        }

    }

}