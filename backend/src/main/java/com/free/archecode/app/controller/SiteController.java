package com.free.archecode.app.controller;

import com.free.archecode.app.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SiteController {

    @GetMapping("/")
    public Map<String, String> index() {
        System.out.println("index");
//        return new HashMap<>().put("message", "hello");
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Hello World");
        User newUser = new User();

        return map;
    }
}
