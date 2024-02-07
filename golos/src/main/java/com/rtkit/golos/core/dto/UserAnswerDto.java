package com.rtkit.golos.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserAnswerDto {
    private Integer resultId;
    private Integer pollQuestionId;
    private Integer answerId;
    private String userAnswer;
}
