package com.rtkit.golos.core.dto;

import com.rtkit.golos.core.web.request.AddAnswerRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AddQuestionAnswerDto {
    private Integer pollQuestionId;
    private List<AddAnswerDto> answers;
}
