package com.rtkit.golos.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PollQuestionDto {
    private Integer id;
    private Integer pollId;
    private QuestionDto question;
}
