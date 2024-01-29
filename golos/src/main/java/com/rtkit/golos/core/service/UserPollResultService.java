package com.rtkit.golos.core.service;

import com.rtkit.golos.core.access.UserPollResultRepo;
import com.rtkit.golos.core.dto.StatDto;
import com.rtkit.golos.core.web.request.AddUserPollResultRequest;
import com.rtkit.golos.core.dto.UserPollResultDto;
import com.rtkit.golos.core.entity.UserPollResult;
import com.rtkit.golos.core.entity.UserPollStatus;
import com.rtkit.golos.core.exception.NotFoundException;
import com.rtkit.golos.core.mapper.UserPollResultMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserPollResultService {
    private final UserPollResultRepo userPollResultRepo;
    private final UserPollResultMapper userMapper;

    public UserPollResultDto getResultById(Integer userId) {
        return userMapper.toDto(userPollResultRepo.getReferenceById(userId));
    }

    public List<UserPollResultDto> getParticipationByUserId(Integer userId) {
        return userMapper.toDto(userPollResultRepo.findByUserId(userId));
    }

    public List<UserPollResultDto> getAllUserResults() {
        List<UserPollResultDto> userList = new ArrayList<>();
        for (UserPollResult result : userPollResultRepo.findAll())
            userList.add(new UserPollResultDto(result));
        return userList;
    }

    public UserPollResultDto addResult(AddUserPollResultRequest newResult) {
        UserPollResult createdUser = userMapper.toEntity(newResult);
        createdUser.setStatus(UserPollStatus.ONGOING);
        UserPollResult createdPoll = userPollResultRepo.saveAndFlush(createdUser);
        return userMapper.toDto(createdPoll);
    }

    public UserPollResultDto updateResultPollStatus(Integer resultId, String statusName) {
        UserPollStatus resultStatus = userMapper.toUserPollResult(statusName);
        UserPollResult foundResult = userPollResultRepo.findById(resultId)
                .orElseThrow(() -> new NotFoundException("Опрос с id:%d не найден".formatted(resultId)));
        foundResult.setStatus(resultStatus);
        UserPollResult updatedResult = userPollResultRepo.save(foundResult);
        return userMapper.toDto(updatedResult);
    }

    public StatDto getStatistics(Integer pollId) {
        Integer totalVoters = userPollResultRepo.countAllByPollIdId(pollId);
        Integer finishedCount = userPollResultRepo.countAllByPollIdAndStatus(pollId, UserPollStatus.COMPLETED.name());
        return new StatDto(totalVoters, finishedCount, totalVoters - finishedCount);
    }
}
