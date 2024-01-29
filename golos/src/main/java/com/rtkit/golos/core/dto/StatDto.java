package com.rtkit.golos.core.dto;

public record StatDto(int totalVoters, int finishedCount, int droppedCount) {
    @Override
    public String toString() {
        return "StatDto{" +
                "totalVoters=" + totalVoters +
                ", finishedCount=" + finishedCount +
                ", droppedCount=" + droppedCount +
                '}';
    }
}
