package com.rtkit.golos.core.service;

import com.rtkit.golos.core.dto.PollQuestionDto;
import com.rtkit.golos.core.dto.QuestionDto;

import java.util.List;

public interface QuestionService {
    List<PollQuestionDto> getAllQuestionsByPollId(int pollId);
    PollQuestionDto getPollQuestionById(int id);
    QuestionDto getQuestionById(int id);
    PollQuestionDto addQuestion(PollQuestionDto pollQuestionDto);
    void deleteQuestion(Integer id);
    QuestionDto updateQuestionText(Integer id, QuestionDto questionDto);
    QuestionDto updateQuestionType(Integer id, QuestionDto questionDto);

}
