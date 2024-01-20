package com.rtkit.golos.core.controller;

import com.rtkit.golos.core.dto.UserCreateDto;
import com.rtkit.golos.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") int userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserCreateDto request) {
        return ResponseEntity.ok(userService.addUser(request));
    }

    @PatchMapping("/{userId}/role/{userRole}")
    public ResponseEntity<?> updatePollStatus(@PathVariable("userId") int userId,
                                              @PathVariable("userRole") String userRole) {
        return ResponseEntity.ok(userService.updateUserRole(userId, userRole));
    }
}
