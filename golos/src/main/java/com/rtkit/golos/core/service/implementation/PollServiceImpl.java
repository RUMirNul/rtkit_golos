package com.rtkit.golos.core.service.implementation;

import com.rtkit.golos.core.access.PollRepo;
import com.rtkit.golos.core.service.PollService;
import com.rtkit.golos.core.web.request.AddPollRequest;
import com.rtkit.golos.core.dto.PollDto;
import com.rtkit.golos.core.dto.PollStatusDto;
import com.rtkit.golos.core.web.request.UpdatePollRequest;
import com.rtkit.golos.core.entity.Poll;
import com.rtkit.golos.core.entity.PollStatus;
import com.rtkit.golos.core.exception.NotFoundException;
import com.rtkit.golos.core.mapper.PollMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@AllArgsConstructor
public class PollServiceImpl implements PollService {
    private final PollRepo pollRepo;
    private final PollMapper pollMapper;

    @Override
    public PollDto getPollById(Integer pollId) {
        Poll foundPoll = pollRepo.findById(pollId)
                .orElseThrow(() -> new NotFoundException("Опрос с id:%d не найден".formatted(pollId)));
        log.info("Найденный результат: {}", foundPoll);

        PollDto foundPollDto = pollMapper.toDto(foundPoll);
        log.info("Сопоставленный объект UserDto: {}", foundPollDto);

        return foundPollDto;
    }

    @Override
    public List<PollDto> getPollByUserId(Integer userId) {
        List<PollDto> userPollDtoList = pollRepo.findByUserId(userId).stream()
                .map(PollDto::new).toList();
        log.info("Найденный результат: {}", userPollDtoList);

        return userPollDtoList;
    }

    @Override
    public List<PollDto> getAllPolls() {
        List<PollDto> pollDtoList = pollRepo.findAll().stream()
                .map(PollDto::new).toList();
        log.info("Найденный результат: {}", pollDtoList);

        return pollDtoList;
    }

    @Override
    public PollStatusDto getAllStatuses() {
        List<String> statusDto = Stream.of(PollStatus.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        log.info("Найденный результат: {}", statusDto);

        PollStatusDto pollStatusDto = new PollStatusDto(statusDto, statusDto.size());
        log.info("Сопоставленный объект PollStatusDto: {}", pollStatusDto);

        return pollStatusDto;
    }

    @Override
    @Transactional
    public PollDto addPoll(AddPollRequest createPollDto) {
        PollDto requestDto = pollMapper.toDto(createPollDto);
        Poll savedPoll = pollMapper.toEntity(requestDto);
        log.info("Сопоставленный объект Poll: {}", savedPoll);

        Poll createdPoll = pollRepo.saveAndFlush(savedPoll);
        log.info("Сохраненный сущность в БД: {}", createdPoll);

        PollDto createdPollDto = pollMapper.toDto(createdPoll);
        log.info("Сопоставленный объект createdPollDto: {}", createdPollDto);

        return createdPollDto;
    }

    @Override
    public PollDto updatePollDto(UpdatePollRequest pollUpdateDto) {
        PollDto requestDto = pollMapper.toDto(pollUpdateDto);
        Poll newPoll = pollMapper.toEntity(requestDto);
        log.info("Сопоставленный объект Poll: {}", newPoll);

        Poll updatedPoll = pollRepo.saveAndFlush(newPoll);
        log.info("Обновленная сущность в БД: {}", updatedPoll);

        PollDto updatedPollDto = pollMapper.toDto(updatedPoll);
        log.info("Сопоставленный объект PollDto: {}", updatedPollDto);

        return updatedPollDto;
    }

    @Override
    public PollDto updatePollStatus(Integer pollId, String pollStatus) {
        return updatePollStatus(pollId, pollMapper.toPollStatus(pollStatus));
    }

    @Override
    public boolean deletePoll(Integer pollId) {
        return updatePollStatus(pollId, PollStatus.CLOSED).getStatus() == PollStatus.CLOSED;
    }

    @Override
    public PollDto updatePollStatus(Integer pollId, PollStatus status) {
        Poll foundPoll = pollRepo.findById(pollId)
                .orElseThrow(() -> new NotFoundException("Опрос с id:%d не найден".formatted(pollId)));
        log.info("Найденный результат: {}", foundPoll);

        foundPoll.setStatus(status);
        Poll updatedPoll = pollRepo.save(foundPoll);
        log.info("Сохраненный сущность в БД: {}", updatedPoll);

        PollDto updatedPollDto = pollMapper.toDto(updatedPoll);
        log.info("Сопоставленный объект createdPollDto: {}", updatedPollDto);

        return updatedPollDto;
    }
}
