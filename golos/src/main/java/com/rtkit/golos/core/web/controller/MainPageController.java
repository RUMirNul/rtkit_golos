package com.rtkit.golos.core.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/login")
    public String login() {
        return "/logReg/login";
    }

    @GetMapping
    public String home() {
        return "/home/index";
    }
}