package com.rtkit.golos.core.service;

import com.rtkit.golos.core.dto.StatDto;
import com.rtkit.golos.core.dto.UserPollResultDto;
import com.rtkit.golos.core.web.request.AddUserPollResultRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserPollResultService {
    UserPollResultDto getResultById(Integer userId);

    List<UserPollResultDto> getParticipationByUserId(Integer userId);

    List<UserPollResultDto> getAllUserResults();

    @Transactional
    UserPollResultDto addResult(AddUserPollResultRequest newResult);

    UserPollResultDto updateResultPollStatus(Integer resultId, String statusName);

    StatDto getStatistics(Integer pollId);
}
