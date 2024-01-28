package com.rtkit.golos.core.mapper;

import com.rtkit.golos.core.dto.PollQuestionDto;
import com.rtkit.golos.core.dto.QuestionDto;
import com.rtkit.golos.core.entity.PollQuestion;
import com.rtkit.golos.core.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionDto toQuestionDto(Question question);

    List<QuestionDto> toQuestionDtos(List<Question> questions);

    @Mapping(target = "pollId", expression = "java(pollQuestion.getPollId().getId())")
    PollQuestionDto toPollQuestionDto(PollQuestion pollQuestion);

    List<PollQuestionDto> toPollQuestionDtos(List<PollQuestion> pollQuestions);

    Question toQuestion(QuestionDto questionDto);
}
