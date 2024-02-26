package com.rtkit.golos.core.web.controller;


import com.rtkit.golos.core.dto.PollDto;
import com.rtkit.golos.core.dto.UserDto;
import com.rtkit.golos.core.service.PollService;
import com.rtkit.golos.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LKPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private PollService pollService;

    @GetMapping("/lk")
    public String lkPage(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto user = userService.getUserByEmail(email);
        model.addAttribute("user", user);

        int id = userService.getGolosUserByEmail(email).getId();
        List<PollDto> pollDtoList = pollService.getPollByUserId(id);
        model.addAttribute("userPolls", pollDtoList);

        return "/lk/LK";
    }
}
