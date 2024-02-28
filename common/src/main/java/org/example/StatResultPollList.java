package org.example;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StatResultPollList {
    private String email;
    List<StatResultPoll> pollAnswerDtoList;
}
