package com.rtkit.golos.core.controller;


import com.rtkit.golos.core.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;


}