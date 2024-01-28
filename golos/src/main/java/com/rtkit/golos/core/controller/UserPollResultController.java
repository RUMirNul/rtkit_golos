package com.rtkit.golos.core.controller;

import com.rtkit.golos.core.web.request.AddUserPollResultRequest;
import com.rtkit.golos.core.service.UserPollResultService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/poll/participation")
public class UserPollResultController {
    private final UserPollResultService userPollResultService;

    @GetMapping("/{resultId}")
    public ResponseEntity<?> getResultById(@PathVariable("resultId") int resultId){
        return ResponseEntity.ok(userPollResultService.getResultById(resultId));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllResultsById(){
        return ResponseEntity.ok(userPollResultService.getAllUserResults());
    }

    @PostMapping
    public ResponseEntity<?> addPoll(@RequestBody AddUserPollResultRequest request) {
        return ResponseEntity.ok(userPollResultService.addResult(request));
    }

    @PatchMapping("/{pollId}/status/{statusName}")
    public ResponseEntity<?> updatePollStatus(@PathVariable("pollId") int pollId,
                                              @PathVariable("statusName") String statusName) {
        return ResponseEntity.ok(userPollResultService.updateResultPollStatus(pollId, statusName));
    }
}
