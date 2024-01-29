package com.rtkit.golos.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ResultDto {
    private String totalVoters;
    private String finishedCount;
    private String droppedCount;

    @Override
    public String toString() {
        return "ResultDto{" +
                "totalVoters='" + totalVoters + '\'' +
                ", finishedCount='" + finishedCount + '\'' +
                ", droppedCount='" + droppedCount + '\'' +
                '}';
    }
}
