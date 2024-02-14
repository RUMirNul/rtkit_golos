package com.rtkit.golos.core.service;

import com.rtkit.golos.core.dto.AnswerDto;
import com.rtkit.golos.core.entity.Answer;

public interface AnswerService {
    Answer saveAnswer(AnswerDto answerDto);
    AnswerDto getAnswer(int id);

    void deleteAnswer(int id);
}
