package com.dmitrromashov.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ATMController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }
}
