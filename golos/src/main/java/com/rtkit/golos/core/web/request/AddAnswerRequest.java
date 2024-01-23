package com.rtkit.golos.core.web.request;

import com.rtkit.golos.core.entity.AnswerType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddAnswerRequest {
    private AnswerType type;
    private String content;
}
