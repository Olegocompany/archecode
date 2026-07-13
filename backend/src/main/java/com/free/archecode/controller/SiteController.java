package com.free.archecode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SiteController {

    @GetMapping("/")
    public Map<String, String> index() {
        System.out.println("index");
        return Map.of("message", "Hello World");
    }
}
