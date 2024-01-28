package com.rtkit.golos.core.controller;

import com.rtkit.golos.core.web.request.AddUserRequest;
import com.rtkit.golos.core.dto.UserDto;
import com.rtkit.golos.core.service.PollService;
import com.rtkit.golos.core.service.PublishService;
import com.rtkit.golos.core.service.UserPollResultService;
import com.rtkit.golos.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final PublishService publishService;
    private final PollService pollService;
    private final UserPollResultService userPollResultService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") int userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/{userId}/polls")
    public ResponseEntity<?> getPollsByUserId(@PathVariable("userId") int userId){
        return ResponseEntity.ok(pollService.getPollByUserId(userId));
    }

    @GetMapping("/{userId}/participation")
    public ResponseEntity<?> getParticipationByUserId(@PathVariable("userId") int userId){
        return ResponseEntity.ok(userPollResultService.getParticipationByUserId(userId));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody AddUserRequest request) {
        UserDto userDto = userService.addUser(request);
        if (userDto != null) publishService.publishRegisterMessage(userDto);
        return ResponseEntity.ok(userDto);
    }

    @PatchMapping("/{userId}/role/{userRole}")
    public ResponseEntity<?> updatePollStatus(@PathVariable("userId") int userId,
                                              @PathVariable("userRole") String userRole) {
        return ResponseEntity.ok(userService.updateUserRole(userId, userRole));
    }
}
