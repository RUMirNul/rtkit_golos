package com.rtkit.golos.core.service;

import com.rtkit.golos.core.dto.PollDto;
import com.rtkit.golos.core.dto.PollStatusDto;
import com.rtkit.golos.core.entity.PollStatus;
import com.rtkit.golos.core.web.request.AddPollRequest;
import com.rtkit.golos.core.web.request.UpdatePollRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PollService {
    PollDto getPollById(Integer pollId);

    List<PollDto> getPollByUserId(Integer userId);

    List<PollDto> getAllPolls();

    PollStatusDto getAllStatuses();

    @Transactional
    PollDto addPoll(AddPollRequest createPollDto);

    PollDto updatePollDto(UpdatePollRequest pollUpdateDto);

    PollDto updatePollStatus(Integer pollId, String pollStatus);

    PollDto updatePollStatus(Integer pollId, PollStatus status);

    boolean deletePoll(Integer pollId);
}
