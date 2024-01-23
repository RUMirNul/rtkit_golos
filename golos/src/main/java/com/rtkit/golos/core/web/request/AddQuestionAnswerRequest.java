package com.rtkit.golos.core.web.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AddQuestionAnswerRequest {
    private Integer pollQuestionId;
    private List<AddAnswerRequest> answers;
}
