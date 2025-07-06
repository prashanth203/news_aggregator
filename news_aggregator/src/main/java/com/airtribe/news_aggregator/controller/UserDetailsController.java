package com.airtribe.news_aggregator.controller;

import com.airtribe.news_aggregator.entity.AuthUser;
import com.airtribe.news_aggregator.model.AuthUserModel;
import com.airtribe.news_aggregator.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hello_world")
public class UserDetailsController {

    @Autowired
    private AuthService authService;

    @GetMapping
    public String getHelloWorld() {
        return "Hello, world!";
    }

    @GetMapping("/user")
    public String getUser() {
        return "Prashanth";
    }

    @PostMapping("/register")
    public AuthUser register(@RequestBody AuthUserModel user) {
        return authService.register(user);
    }
}
