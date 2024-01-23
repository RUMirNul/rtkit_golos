package com.rtkit.golos.core.mapper;

import com.rtkit.golos.core.dto.AddAnswerDto;
import com.rtkit.golos.core.dto.AnswerDto;
import com.rtkit.golos.core.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class AnswerMapper {
    public abstract AnswerDto toDto(Answer source);

    @Mapping(target = "id", ignore = true)
    public abstract AnswerDto toDto(AddAnswerDto source);

    public abstract Answer toEntity(AnswerDto source);


}
