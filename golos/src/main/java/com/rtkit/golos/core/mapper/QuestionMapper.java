package com.rtkit.golos.core.mapper;

import com.rtkit.golos.core.dto.PollQuestionDto;
import com.rtkit.golos.core.dto.QuestionDto;
import com.rtkit.golos.core.entity.PollQuestion;
import com.rtkit.golos.core.entity.Question;
import com.rtkit.golos.core.web.request.AddPollQuestionRequest;
import com.rtkit.golos.core.web.request.AddQuestionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(target = "text", source = "content")
    QuestionDto toQuestionDto(Question question);

    List<QuestionDto> toQuestionDtos(List<Question> questions);

    @Mapping(target = "pollId", expression = "java(pollQuestion.getPollId().getId())")
    @Mapping(target = "question", source = "questionId")
    PollQuestionDto toPollQuestionDto(PollQuestion pollQuestion);

    List<PollQuestionDto> toPollQuestionDtos(List<PollQuestion> pollQuestions);

    @Mapping(target = "content", source = "text")
    Question toQuestion(QuestionDto questionDto);

    @Mapping(target = "id", ignore = true)
    PollQuestionDto toPollQuestionDto(AddPollQuestionRequest source);

    @Mapping(target = "id", ignore = true)
    QuestionDto toQuestionDto(AddQuestionRequest source);

}
