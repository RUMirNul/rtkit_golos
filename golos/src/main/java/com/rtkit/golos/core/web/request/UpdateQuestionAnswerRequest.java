package com.rtkit.golos.core.web.request;

import com.rtkit.golos.core.entity.AnswerType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateQuestionAnswerRequest {
    private Integer id;
    private AnswerType type;
    private String content;
    private Integer nextQuestionId;
}
