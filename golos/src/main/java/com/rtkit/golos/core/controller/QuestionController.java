package com.rtkit.golos.core.controller;


import com.rtkit.golos.core.dto.PollQuestionDto;
import com.rtkit.golos.core.dto.QuestionDto;
import com.rtkit.golos.core.mapper.QuestionMapper;
import com.rtkit.golos.core.service.QuestionService;
import com.rtkit.golos.core.web.request.AddPollQuestionRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;


    @PostMapping
    public PollQuestionDto createQuestion(@RequestBody AddPollQuestionRequest request) {
//        PollQuestionDto pollQuestionDto = new PollQuestionDto();
//        pollQuestionDto.setPollId(request.getPollId());
//        QuestionDto questionDto = new QuestionDto();
//        questionDto.setType(request.getQuestion().getType());
//        questionDto.setText(request.getQuestion().getText());
//        questionDto.setQuestionOrder(request.getQuestion().getQuestionOrder());
//        pollQuestionDto.setQuestion(questionDto);

        PollQuestionDto pqd = questionMapper.toPollQuestionDto(request);
        return questionService.addQuestion(pqd);
    }

    @GetMapping("/{id}")
    public PollQuestionDto getQuestion(@PathVariable("id") int id) {
        return questionService.getPollQuestionById(id);
    }
}