package com.free.archecode.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteController {

    @Value("${filesystem.bucket-usersAvatars}")
    private String bucketAvatars;

    @GetMapping("/")
    public Map<String, String> index() {
        System.out.println("index");
        return Map.of("message", "Hello World");
    }

}
