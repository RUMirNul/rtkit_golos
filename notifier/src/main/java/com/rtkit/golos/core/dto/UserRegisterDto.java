package com.rtkit.golos.core.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UserRegisterDto(String email, String activationCode) {
    @JsonCreator
    public UserRegisterDto(@JsonProperty("email") String email,
                           @JsonProperty("activationCode") String activationCode) {
        this.email = email;
        this.activationCode = activationCode;
    }
}
