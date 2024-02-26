package com.rtkit.golos.core.web.controller;

import com.rtkit.golos.core.entity.UserRole;
import com.rtkit.golos.core.service.UserService;
import com.rtkit.golos.core.web.request.AddUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/registration")
public class RegistrationPageController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showRegistrationForm(@ModelAttribute("user") AddUserRequest newUser) {
        return "/logReg/registration";
    }

    @PostMapping
    public String registerUserAccount(AddUserRequest newUser) {
        newUser.setRole(UserRole.USER);

        try {
            userService.addUser(newUser);
        } catch (Exception e) {
            return "redirect:/registration?error";
        }
        return "redirect:/registration?success";
    }
}
