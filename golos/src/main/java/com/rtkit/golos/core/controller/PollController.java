package com.rtkit.golos.core.controller;

import com.rtkit.golos.core.dto.PollDtoList;
import com.rtkit.golos.core.web.request.AddInviteRequest;
import com.rtkit.golos.core.dto.InviteQueueDto;
import com.rtkit.golos.core.web.request.AddPollRequest;
import com.rtkit.golos.core.web.request.UpdatePollRequest;
import com.rtkit.golos.core.service.InviteService;
import com.rtkit.golos.core.service.PollService;
import com.rtkit.golos.core.service.PublishService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/poll")
public class PollController {
    private final PollService pollService;
    private final InviteService inviteService;
    private final PublishService publishService;

    @GetMapping("/status")
    public ResponseEntity<?> getAllStatues() {
        return ResponseEntity.ok(pollService.getAllStatuses());
    }

    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/{pollId}")
    public ResponseEntity<?> getPollById(@PathVariable("pollId") int pollId) {
        return ResponseEntity.ok(pollService.getPollById(pollId));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllPolls() {
        return ResponseEntity.ok(new PollDtoList(pollService.getAllPolls()));
    }

    @PostMapping
    public ResponseEntity<?> addPoll(@RequestBody AddPollRequest request) {
        return ResponseEntity.ok(pollService.addPoll(request));
    }

    @PutMapping
    public ResponseEntity<?> updatePoll(@RequestBody UpdatePollRequest request) {
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
    public ResponseEntity<?> getInvite(@PathVariable("inviteId") int inviteId,
                                       @PathVariable("pollId") int pollId) {
        return ResponseEntity.ok(inviteService.getInvite(inviteId));
    }

    @PostMapping("/{pollId}/invite")
    public ResponseEntity<?> createInvite(@RequestBody AddInviteRequest request,
                                          @PathVariable("pollId") int pollId) {
        return ResponseEntity.ok(inviteService.addInvite(pollId, request));
    }

    @PostMapping("/{pollId}/invite/{inviteId}")
    public void sendEmail(@PathVariable("pollId") int pollId,
                          @PathVariable("inviteId") int inviteId,
                          @RequestBody InviteQueueDto request) {
        String name = pollService.getPollById(pollId).getName();
        log.info("Запрос на рассылки приглашений: {}", request);
        publishService.publishInviteMessage(name, inviteId, request);
    }
}
