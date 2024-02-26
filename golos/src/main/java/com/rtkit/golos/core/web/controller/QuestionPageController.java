package com.rtkit.golos.core.web.controller;


import com.rtkit.golos.core.dto.PollQuestionDto;
import com.rtkit.golos.core.dto.QuestionDto;
import com.rtkit.golos.core.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/polls/{pollid}/questions")
public class QuestionPageController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/{questid}")
    public String show(Model model,
                       @PathVariable("pollid") int pollId,
                       @PathVariable("questid") int questId) {
        model.addAttribute("pollid", pollId);

        PollQuestionDto pollQuestionDto = questionService.getAllQuestionsByPollId(pollId).get(questId - 1);
        model.addAttribute("pollQuestionDto", pollQuestionDto);

        QuestionDto question = pollQuestionDto.getQuestion();
        model.addAttribute("questionDTO", question);

        return "/question/show";
    }
}
