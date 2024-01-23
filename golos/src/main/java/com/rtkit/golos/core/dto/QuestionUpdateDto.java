package com.rtkit.golos.core.dto;

import com.rtkit.golos.core.entity.QuestionType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionUpdateDto {
    private Integer questionOrder;
    private String text;
    private QuestionType type;
}