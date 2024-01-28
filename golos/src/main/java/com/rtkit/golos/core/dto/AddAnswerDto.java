package com.rtkit.golos.core.dto;

import com.rtkit.golos.core.entity.AnswerType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddAnswerDto {
    private AnswerType type;
    private String content;
    private Integer nextQuestionId;
}
