package com.rtkit.golos.core.dto;

import com.rtkit.golos.core.entity.AnswerType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnswerDto {
    private Integer id;
    private AnswerType type;
    private Integer nextQuestionId;
}
