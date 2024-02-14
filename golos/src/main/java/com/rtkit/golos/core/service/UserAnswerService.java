package com.rtkit.golos.core.service;

import com.rtkit.golos.core.dto.UserAnswerDto;

public interface UserAnswerService {
    UserAnswerDto saveUserAnswer(UserAnswerDto newUserAnswer);
    UserAnswerDto getUserAnswer(int resultId, int pollQuestionId);
    UserAnswerDto updateUserAnswer(UserAnswerDto newUserAnswer);

    void deleteUserAnswer(int resultId, int pollQuestionId);
}
