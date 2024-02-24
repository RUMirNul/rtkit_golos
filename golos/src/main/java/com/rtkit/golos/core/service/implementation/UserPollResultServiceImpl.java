package com.rtkit.golos.core.service.implementation;

import com.rtkit.golos.core.access.UserPollResultRepo;
import com.rtkit.golos.core.dto.PollDto;
import com.rtkit.golos.core.dto.StatDto;
import com.rtkit.golos.core.dto.UserDto;
import com.rtkit.golos.core.entity.PollStatus;
import com.rtkit.golos.core.service.PollService;
import com.rtkit.golos.core.service.UserPollResultService;
import com.rtkit.golos.core.service.UserService;
import com.rtkit.golos.core.web.request.AddUserPollResultRequest;
import com.rtkit.golos.core.dto.UserPollResultDto;
import com.rtkit.golos.core.entity.UserPollResult;
import com.rtkit.golos.core.entity.UserPollStatus;
import com.rtkit.golos.core.exception.NotFoundException;
import com.rtkit.golos.core.mapper.UserPollResultMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class UserPollResultServiceImpl implements UserPollResultService {
    private final UserPollResultRepo userPollResultRepo;
    private final UserPollResultMapper resultMapper;
    private final UserService userService;
    private final PollService pollService;

    @Override
    public UserPollResultDto getResultById(Integer resultId) {
        UserPollResult foundResult = userPollResultRepo.findById(resultId)
                .orElseThrow(() -> new NotFoundException("Результат с id:%d не найден.".formatted(resultId)));
        log.info("Найденный результат: {}", foundResult);

        UserPollResultDto foundResultDto = resultMapper.toDto(foundResult);
        log.info("Сопоставленный объект UserPollResultDto: {}", foundResultDto);

        return foundResultDto;
    }

    @Override
    public List<UserPollResultDto> getParticipationByUserId(Integer userId) {
        List<UserPollResultDto> foundResult = userPollResultRepo.findByUserId(userId).stream()
                .map(UserPollResultDto::new)
                .toList();
        log.info("Найденный результат: {}", foundResult);

        return foundResult;
    }

    @Override
    public List<UserPollResultDto> getAllUserResults() {
        List<UserPollResultDto> resultList = userPollResultRepo.findAll().stream()
                .map(UserPollResultDto::new).toList();
        log.info("Найденный результат: {}", resultList);

        return resultList;
    }

    @Override
    @Transactional
    public UserPollResultDto addResult(AddUserPollResultRequest newResult) {
        UserPollResult createdUser = resultMapper.toEntity(newResult);
        createdUser.setStatus(UserPollStatus.ONGOING);

        PollDto foundPoll = pollService.getPollById(createdUser.getPollId().getId());
        if (foundPoll == null || (foundPoll.getStatus() != PollStatus.PUBLIC && foundPoll.getStatus() != PollStatus.HIDDEN))
            throw new NotFoundException("Не найден опрос с id:" + newResult.getPollId());

        UserDto foundUser = userService.getUserById(createdUser.getUserId().getId());
        if (foundUser == null || !Objects.equals(foundUser.getEmail(), SecurityContextHolder.getContext().getAuthentication().getName()))
            throw new NotFoundException("Не найден пользователь с id:" + newResult.getPollId());

        UserPollResult savedResult = userPollResultRepo.saveAndFlush(createdUser);
        log.info("Сохраненная сущность UserPollResult: {}", savedResult);

        UserPollResultDto savedResultDto = resultMapper.toDto(savedResult);
        log.info("Сопоставленный объект UserPollResultDto: {}", savedResultDto);

        return savedResultDto;
    }

    @Override
    public UserPollResultDto updateResultPollStatus(Integer resultId, String statusName) {
        UserPollStatus resultStatus = resultMapper.toUserPollResult(statusName);
        UserPollResult foundResult = userPollResultRepo.findById(resultId)
                .orElseThrow(() -> new NotFoundException("Результаты опроса с id:%d не найден".formatted(resultId)));
        log.info("Найденный результат: {}", foundResult);

        foundResult.setStatus(resultStatus);
        UserPollResult updatedResult = userPollResultRepo.save(foundResult);
        UserPollResultDto foundResultDto = resultMapper.toDto(updatedResult);
        log.info("Сопоставленный объект UserPollResultDto: {}", foundResultDto);

        return foundResultDto;
    }

    @Override
    public StatDto getStatistics(Integer pollId) {
        Integer totalVoters = userPollResultRepo.countAllByPollIdId(pollId);
        Integer finishedCount = userPollResultRepo.countAllByPollIdAndStatus(pollId, UserPollStatus.COMPLETED.name());
        StatDto statDto = new StatDto(totalVoters, finishedCount, totalVoters - finishedCount);
        log.info("Найденный результат: {}", statDto);

        return statDto;
    }
}
