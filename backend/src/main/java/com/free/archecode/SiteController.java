package com.free.archecode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.util.JSONPObject;

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
        return map;
    }
}
