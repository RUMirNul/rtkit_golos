package com.rtkit.golos.core.web.controller;


import com.rtkit.golos.core.dto.PollDto;
import com.rtkit.golos.core.entity.GolosUser;
import com.rtkit.golos.core.entity.Poll;
import com.rtkit.golos.core.service.PollService;
import com.rtkit.golos.core.service.QuestionService;
import com.rtkit.golos.core.service.UserService;
import com.rtkit.golos.core.web.request.AddPollRequest;
import com.rtkit.golos.core.web.request.UpdatePollRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/polls")
public class PollsPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private PollService pollService;

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("polls", pollService.getAllPolls());

        return "/poll/allPolls";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id) {

        model.addAttribute("id", id);
        model.addAttribute("poll", pollService.getPollById(id));
        model.addAttribute("pollquestion", questionService.getAllQuestionsByPollId(id));

        return "/poll/show";
    }

    @GetMapping("/create")
    public String createPage(@ModelAttribute("poll") Poll poll) {
        return "/poll/new";
    }

    @PostMapping("/create")
    public String createPoll(AddPollRequest newPoll) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        GolosUser user = userService.getGolosUserByEmail(email);

        newPoll.setAuthorId(user.getId());
        pollService.addPoll(newPoll);

        return "redirect:/polls";
    }

    @GetMapping("/{pollid}/end")
    public String end(Model model,
                      @PathVariable("pollid") int id) {
        PollDto pollDto = pollService.getPollById(id);
        model.addAttribute("poll", pollDto);

        return "/poll/end";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("poll", pollService.getPollById(id));

        return "/poll/edit";
    }

    @PutMapping("/{id}")
    public String update(@ModelAttribute("poll") UpdatePollRequest poll) {

        pollService.updatePollDto(poll);

        return "redirect:/lk";
    }

    @PatchMapping("/{id}/publish")
    public String publish(@PathVariable("id") int id) {
        pollService.updatePollStatus(id, "PUBLIC");

        return "redirect:/lk";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        pollService.deletePoll(id);

        return "redirect:/lk";
    }
}
