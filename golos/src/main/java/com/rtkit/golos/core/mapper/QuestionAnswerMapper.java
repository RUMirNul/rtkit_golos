package com.rtkit.golos.core.mapper;

import com.rtkit.golos.core.dto.AddAnswerDto;
import com.rtkit.golos.core.dto.AddQuestionAnswerDto;
import com.rtkit.golos.core.dto.QuestionAnswerDto;
import com.rtkit.golos.core.entity.ImageAnswer;
import com.rtkit.golos.core.entity.TextAnswer;
import com.rtkit.golos.core.entity.UserTextAnswer;
import com.rtkit.golos.core.web.request.AddAnswerRequest;
import com.rtkit.golos.core.web.request.AddQuestionAnswerRequest;
import com.rtkit.golos.core.web.request.UpdateQuestionAnswerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class QuestionAnswerMapper {
    public abstract AddQuestionAnswerDto addQuestionAnswerRequestToAddQuestionAnswerDto(AddQuestionAnswerRequest source);
    public abstract AddAnswerDto addAnswerRequestToAddAnswerDto(AddAnswerRequest source);
    public abstract QuestionAnswerDto toDto(UpdateQuestionAnswerRequest source);
    @Mapping(target = "id", expression = "java(source.getId())")
    @Mapping(target = "type", expression = "java(source.getAnswer().getType())")
    @Mapping(target = "content", expression = "java(source.getContent())")
    @Mapping(target = "nextQuestionId", expression = "java(source.getAnswer().getNextQuestionId())")
    public abstract QuestionAnswerDto toDto(TextAnswer source);
    @Mapping(target = "id", expression = "java(source.getId())")
    @Mapping(target = "type", expression = "java(source.getAnswer().getType())")
    @Mapping(target = "content", expression = "java(source.getImagePath())")
    @Mapping(target = "nextQuestionId", expression = "java(source.getAnswer().getNextQuestionId())")
    public abstract QuestionAnswerDto toDto(ImageAnswer source);
    @Mapping(target = "id", expression = "java(source.getId())")
    @Mapping(target = "type", expression = "java(source.getAnswer().getType())")
    @Mapping(target = "content", expression = "java(source.getPreparedText().toString())")
    @Mapping(target = "nextQuestionId", expression = "java(source.getAnswer().getNextQuestionId())")
    public abstract QuestionAnswerDto toDto(UserTextAnswer source);
}
