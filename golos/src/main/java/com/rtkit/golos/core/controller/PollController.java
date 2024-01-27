package com.rtkit.golos.core.controller;

import com.rtkit.golos.core.dto.InviteCreateDto;
import com.rtkit.golos.core.dto.InviteQueueDto;
import com.rtkit.golos.core.dto.PollCreateDto;
import com.rtkit.golos.core.dto.PollUpdateDto;
import com.rtkit.golos.core.service.InviteService;
import com.rtkit.golos.core.service.PollService;
import com.rtkit.golos.core.service.PublishService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/poll")
public class PollController {
    private final PollService pollService;
    private final InviteService inviteService;
    private final PublishService publishService;

    @GetMapping("/status")
    public ResponseEntity<?> getAllStatues(){
        return ResponseEntity.ok(pollService.getAllStatuses());
    }

    @GetMapping("/{pollId}")
    public ResponseEntity<?> getPollById(@PathVariable("pollId") int pollId){
        return ResponseEntity.ok(pollService.getPollById(pollId));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllPolls(){
        return ResponseEntity.ok(pollService.getAllPolls());
    }

    @PostMapping
    public ResponseEntity<?> addPoll(@RequestBody PollCreateDto request) {
        return ResponseEntity.ok(pollService.addPoll(request));
    }

    @PutMapping
    public ResponseEntity<?> updatePoll(@RequestBody PollUpdateDto request) {
        return ResponseEntity.ok(pollService.updatePollDto(request));
    }

    @PatchMapping("/{pollId}/status/{statusName}")
    public ResponseEntity<?> updatePollStatus(@PathVariable("pollId") int pollId,
                                              @PathVariable("statusName") String statusName) {
        return ResponseEntity.ok(pollService.updatePollStatus(pollId, statusName));
    }

    @DeleteMapping("/{pollId}")
    public ResponseEntity<?> deletePoll(@PathVariable("pollId") int pollId) {
        return ResponseEntity.ok(pollService.deletePoll(pollId));
    }

    @GetMapping("/{pollId}/invite/{inviteId}")
    public ResponseEntity<?> getInvite(@PathVariable("inviteId") int inviteId) {
        return ResponseEntity.ok(inviteService.getInvite(inviteId));
    }

    @PostMapping("/{pollId}/invite")
    public ResponseEntity<?> createInvite(@RequestBody InviteCreateDto request) {
        return ResponseEntity.ok(inviteService.addInvite(request));
    }

    @PostMapping("/{pollId}/invite/{inviteId}")
    public void sendEmail(@PathVariable("pollId") int pollId,
                                       @PathVariable("inviteId") int inviteId,
                                       @RequestBody InviteQueueDto request) {
        String name = pollService.getPollById(pollId).getName();
        publishService.publishInviteMessage(name, inviteId, request);
    }
}
