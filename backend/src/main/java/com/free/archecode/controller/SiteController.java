package com.free.archecode.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/whoami")
    public Map<String, String> whoami() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object currentUserName = authentication.getPrincipal();
//        System.out.println(currentUserName);
        return Map.of("message", "Hello " + currentUserName);
    }
}
