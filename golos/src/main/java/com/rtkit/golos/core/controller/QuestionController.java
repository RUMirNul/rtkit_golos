package com.rtkit.golos.core.controller;


import com.rtkit.golos.core.dto.PollQuestionDto;
import com.rtkit.golos.core.mapper.QuestionMapper;
import com.rtkit.golos.core.service.QuestionService;
import com.rtkit.golos.core.web.request.AddPollQuestionRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @PostMapping
    public PollQuestionDto createQuestion(@RequestBody AddPollQuestionRequest request) {
        log.info("Запрос сохранения нового вопроса: {}", request);

        PollQuestionDto pqd = questionMapper.toPollQuestionDto(request);
        return questionService.addQuestion(pqd);
    }

    @GetMapping("/{id}")
    public PollQuestionDto getQuestion(@PathVariable("id") int id) {
        log.info("Запрос получения вопроса по id: {}", id);

        return questionService.getPollQuestionById(id);
    }
}