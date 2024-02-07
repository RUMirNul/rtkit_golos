package com.rtkit.golos.core.mapper;

import com.rtkit.golos.core.dto.UserAnswerDto;
import com.rtkit.golos.core.entity.UserAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class UserAnswerMapper {
    @Mapping(target = "resultId", source = "id.resultId")
    @Mapping(target = "pollQuestionId", source = "id.pollQuestionId")
    @Mapping(target = "answerId", source = "id.answerId")
    public abstract UserAnswerDto toDto(UserAnswer source);


    @Mapping(target = "id.resultId", source = "resultId")
    @Mapping(target = "id.pollQuestionId", source = "pollQuestionId")
    @Mapping(target = "id.answerId", source = "answerId")
    public abstract UserAnswer toEntity(UserAnswerDto source);
}
