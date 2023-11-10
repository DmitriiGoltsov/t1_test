package com.goltsov.test_t1.controllers;

import com.goltsov.test_t1.dto.CustomStringDto;
import com.goltsov.test_t1.services.CustomStringService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping(path = "/strings")
public class CustomStringController {

    private final CustomStringService customStringService;

    @Autowired
    public CustomStringController(CustomStringService customStringService) {
        this.customStringService = customStringService;
    }

    @PostMapping(path = "")
    public String getCount(@Valid @ModelAttribute("customString") CustomStringDto customString,
                           BindingResult bindingResult,
                           Model model) {

        if (bindingResult.hasErrors()) {
            return "input";
        }

        Map<Character, Integer> characterCount = customStringService.getCharacterCount(customString.getCustomString());
        model.addAttribute("characterCount", characterCount);
        return "show";
    }

    @GetMapping(path = "/new")
    public String newString(Model model) {
        model.addAttribute("customString", new CustomStringDto());
        return "input";
    }
}
