package com.rtkit.golos.core.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewQuestionCreatedEvent {
    private Integer newQuestionId;
}
