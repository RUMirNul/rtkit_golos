package com.rtkit.golos.core.service;

import com.rtkit.golos.core.access.PollRepo;
import com.rtkit.golos.core.web.request.AddPollRequest;
import com.rtkit.golos.core.dto.PollDto;
import com.rtkit.golos.core.dto.PollStatusDto;
import com.rtkit.golos.core.web.request.UpdatePollRequest;
import com.rtkit.golos.core.entity.Poll;
import com.rtkit.golos.core.entity.PollStatus;
import com.rtkit.golos.core.exception.NotFoundException;
import com.rtkit.golos.core.mapper.PollMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Transactional
public class PollService {
    private final PollRepo pollRepo;
    private final PollMapper pollMapper;

    public PollDto getPollById(Integer pollId) {
        return pollMapper.toDto(pollRepo.getReferenceById(pollId));
    }

    public List<PollDto> getPollByUserId(Integer userId) {
        return pollMapper.toDto(pollRepo.findByUserId(userId));
    }

    public List<PollDto> getAllPolls() {
        List<PollDto> pollList = new ArrayList<>();
        for (Poll poll : pollRepo.findAll())
            pollList.add(new PollDto(poll));
        return pollList;
    }

    public PollStatusDto getAllStatuses() {
        List<String> statusDto = Stream.of(PollStatus.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return new PollStatusDto(statusDto, statusDto.size());
    }

    public PollDto addPoll(AddPollRequest createPollDto) {
        PollDto requestDto = pollMapper.toDto(createPollDto);
        Poll newPoll = pollMapper.toModel(requestDto);
        Poll createdPoll = pollRepo.saveAndFlush(newPoll);
        return pollMapper.toDto(createdPoll);
    }

    public PollDto updatePollDto(UpdatePollRequest pollUpdateDto) {
        PollDto requestDto = pollMapper.toDto(pollUpdateDto);
        Poll newPoll = pollMapper.toModel(requestDto);
        Poll createdPoll = pollRepo.saveAndFlush(newPoll);
        return pollMapper.toDto(createdPoll);
    }

    public PollDto updatePollStatus(Integer pollId, String pollStatus) {
        return updatePollStatus(pollId, pollMapper.toPollStatus(pollStatus));
    }

    private PollDto updatePollStatus(Integer pollId, PollStatus status) {
        Poll foundPoll = pollRepo.findById(pollId)
                .orElseThrow(() -> new NotFoundException("Опрос с id:%d не найден".formatted(pollId)));

        foundPoll.setStatus(status);
        Poll updatedPoll = pollRepo.save(foundPoll);
        return pollMapper.toDto(updatedPoll);
    }

    public boolean deletePoll(Integer pollId) {
        return updatePollStatus(pollId, PollStatus.CLOSED).getStatus() == PollStatus.CLOSED;
    }
}
