package com.rtkit.golos.core.web.request;

import com.rtkit.golos.core.entity.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddQuestionRequest {
    private Integer questionOrder;
    private String text;
    private QuestionType type;
}
