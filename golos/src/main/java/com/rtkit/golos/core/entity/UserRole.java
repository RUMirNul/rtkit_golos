package com.rtkit.golos.core.entity;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    MODERATOR("ROLE_MODERATOR"),
    USER("ROLE_USER");

    private final String textRole;

    UserRole(String textRole) {
        this.textRole = textRole;
    }
}