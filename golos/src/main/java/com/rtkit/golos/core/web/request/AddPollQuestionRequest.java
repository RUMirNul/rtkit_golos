package com.rtkit.golos.core.web.request;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddPollQuestionRequest {
    private Integer pollId;
    private AddQuestionRequest question;
}
