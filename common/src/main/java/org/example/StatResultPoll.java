package org.example;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StatResultPoll {
    private String questionText;
    private String answerText;
    private Integer count;
    private Short questionOrder;
}