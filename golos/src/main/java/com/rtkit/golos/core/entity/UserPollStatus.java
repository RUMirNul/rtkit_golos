package com.rtkit.golos.core.entity;

import lombok.Getter;

@Getter
public enum UserPollStatus {
    COMPLETED("COMPLETED"), DROPPED("DROPPED"), ONGOING("ONGOING");

    private final String name;
    UserPollStatus(String name) {
        this.name = name;
    }
}