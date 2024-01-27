package com.rtkit.golos.core.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record InviteDto(String pollName, String email, String url) {
    @JsonCreator
    public InviteDto(@JsonProperty("pollName") String pollName,
                     @JsonProperty("email") String email,
                     @JsonProperty("url") String url) {
        this.pollName = pollName;
        this.email = email;
        this.url = url;
    }
}
