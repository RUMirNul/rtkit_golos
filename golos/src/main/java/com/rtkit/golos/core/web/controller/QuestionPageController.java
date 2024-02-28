package com.rtkit.golos.core.web.controller;


import com.rtkit.golos.core.dto.PollQuestionDto;
import com.rtkit.golos.core.dto.QuestionAnswerDto;
import com.rtkit.golos.core.dto.QuestionDto;
import com.rtkit.golos.core.entity.QuestionAnswer;
import com.rtkit.golos.core.mapper.QuestionMapper;
import com.rtkit.golos.core.service.PollService;
import com.rtkit.golos.core.service.QuestionAnswerService;
import com.rtkit.golos.core.service.QuestionService;
import com.rtkit.golos.core.web.request.AddQuestionRequest;
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

    @Autowired
    private PollService pollService;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/{questid}")
    public String show(Model model,
                       @PathVariable("pollid") int pollId,
                       @PathVariable("questid") int questId) {

        model.addAttribute("pollid", pollId);

        List<PollQuestionDto> pollQuestionDtos = questionService.getAllQuestionsByPollId(pollId);
        model.addAttribute("pollQuestionDtos", pollQuestionDtos);

        PollQuestionDto pollQuestionDto = pollQuestionDtos.get(0);
        for (PollQuestionDto pollQuestionDto1 : pollQuestionDtos) {
            if (pollQuestionDto1.getId() == questId) {
                pollQuestionDto = pollQuestionDto1;
                break;
            }
        }
        model.addAttribute("pollQuestionDto", pollQuestionDto);

        model.addAttribute("questionIndex", pollQuestionDtos.indexOf(pollQuestionDto) + 1);

        QuestionDto question = pollQuestionDto.getQuestion();
        model.addAttribute("questionDTO", question);

        List<QuestionAnswerDto> questionAnswers = questionAnswerService.getQuestionAnswersByPollQuestionId(pollQuestionDto.getId());
        model.addAttribute("questionAnswers", questionAnswers);
        return "/question/show";
    }

    @GetMapping("/create")
    public String createQuestionPage(@ModelAttribute("question") AddQuestionRequest newQuestion,
                                     Model model,
                                     @PathVariable("pollid") int pollId) {

        model.addAttribute("poll", pollService.getPollById(pollId));
        return "/question/new";
    }

    @PostMapping("/create")
    public String createQuestion(@PathVariable("pollid") int pollId,
                                 AddQuestionRequest newQuestion) {
        PollQuestionDto pollQuestionDto = new PollQuestionDto();
        pollQuestionDto.setPollId(pollId);

        QuestionDto questionDto = questionMapper.toQuestionDto(newQuestion);
        pollQuestionDto.setQuestion(questionDto);
        questionDto.setQuestionOrder(0);

        questionService.addQuestion(pollQuestionDto);
        return "redirect:/polls/" + pollId + "/edit";
    }

    @GetMapping("/createAnswer")
    public String createAnswerPage(@PathVariable("pollid") int id) {
        return "redirect:/polls/" + id + "/edit";
    }

    @PostMapping("/createAnswer")
    public String createAnswer(@PathVariable("pollid") int id) {
        return "redirect:/polls/" + id + "/edit";
    }
}
