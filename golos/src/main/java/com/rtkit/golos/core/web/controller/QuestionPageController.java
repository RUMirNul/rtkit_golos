package com.rtkit.golos.core.web.controller;


import com.rtkit.golos.core.dto.PollQuestionDto;
import com.rtkit.golos.core.dto.QuestionAnswerDto;
import com.rtkit.golos.core.dto.QuestionDto;
import com.rtkit.golos.core.entity.QuestionAnswer;
import com.rtkit.golos.core.service.QuestionAnswerService;
import com.rtkit.golos.core.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/polls/{pollid}/questions")
public class QuestionPageController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionAnswerService questionAnswerService;

    @GetMapping("/{questid}")
    public String show(Model model,
                       @PathVariable("pollid") int pollId,
                       @PathVariable("questid") int questId) {
        model.addAttribute("pollid", pollId);

        List<PollQuestionDto> pollQuestionDtos = questionService.getAllQuestionsByPollId(pollId);
        model.addAttribute("pollQuestionDtos", pollQuestionDtos);

        PollQuestionDto pollQuestionDto = pollQuestionDtos.get(questId - 1);
        model.addAttribute("pollQuestionDto", pollQuestionDto);

        model.addAttribute("questionIndex", pollQuestionDtos.indexOf(pollQuestionDto) + 1);

        QuestionDto question = pollQuestionDto.getQuestion();
        model.addAttribute("questionDTO", question);

        List<QuestionAnswerDto> questionAnswers = questionAnswerService.getQuestionAnswersByPollQuestionId(pollQuestionDto.getId());
        model.addAttribute("questionAnswers", questionAnswers);
        return "/question/show";
    }

    @GetMapping("/create")
    public String createQuestionPage() {
        return "/question/new";
    }

    @PostMapping("/create")
    public String createQuestion(@PathVariable("pollid") int id) {
        return "redirect:/polls//edit";
    }
}
