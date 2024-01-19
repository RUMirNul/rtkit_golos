package com.rtkit.golos.core.controller;

import com.rtkit.golos.core.dto.PollCreateDto;
import com.rtkit.golos.core.dto.PollUpdateDto;
import com.rtkit.golos.core.mapper.PollMapper;
import com.rtkit.golos.core.service.PollService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/poll")
public class PollController {
    private final PollService pollService;
    private final PollMapper pollMapper;

    @GetMapping("/status")
    public ResponseEntity<?> getAllStatues(){
        return ResponseEntity.ok(pollService.getAllStatuses());
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllPolls(){
        return ResponseEntity.ok(pollService.getAllPolls());
    }

    @PostMapping
    public ResponseEntity<?> addPoll(@RequestBody PollCreateDto request) {
        return ResponseEntity.ok(pollService.addPoll(pollMapper.toDto(request)));
    }

    @PutMapping
    public ResponseEntity<?> updatePoll(@RequestBody PollUpdateDto request) {
        return ResponseEntity.ok(pollService.updatePollDto(pollMapper.toDto(request)));
    }

    @PatchMapping("/{pollId}/status/{statusName}")
    public ResponseEntity<?> updatePollStatus(@PathVariable("pollId") int pollId,
                                              @PathVariable("statusName") String statusName) {
        return ResponseEntity.ok(pollService.updatePollStatus(pollId, pollMapper.toPollStatus(statusName)));
    }

    @DeleteMapping("/{pollId}")
    public ResponseEntity<?> deletePoll(@PathVariable("pollId") int pollId) {
        return ResponseEntity.ok(pollService.deletePoll(pollId));
    }
}
