package com.rtkit.golos.core.service;

import com.rtkit.golos.core.dto.AddAnswerDto;
import com.rtkit.golos.core.dto.AddQuestionAnswerDto;
import com.rtkit.golos.core.dto.QuestionAnswerDto;
import com.rtkit.golos.core.entity.Answer;
import com.rtkit.golos.core.entity.QuestionAnswer;

import java.util.List;

public interface QuestionAnswerService {
    QuestionAnswerDto addTypedQuestionAnswer(Integer pollQuestionId, AddAnswerDto answer);
    List<QuestionAnswerDto> addNewQuestionAnswer(AddQuestionAnswerDto request);
    List<QuestionAnswerDto> getQuestionAnswersByPollQuestionId(int pollQuestionId);
    void deleteQuestionAnswer(int answerId);
    QuestionAnswerDto updateQuestionAnswer(QuestionAnswerDto newQuestionAnswer);
}
