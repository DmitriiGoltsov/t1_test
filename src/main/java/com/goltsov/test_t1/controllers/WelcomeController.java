package com.goltsov.test_t1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "")
public class WelcomeController {

    @GetMapping(path = "")
    public String getInputPage() {
        return "index";
    }
}
