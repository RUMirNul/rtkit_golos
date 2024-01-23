package com.rtkit.golos.core.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public enum AnswerType {
    @JsonProperty("userText")
    USERTEXT,
    @JsonProperty("text")
    TEXT,
    @JsonProperty("image")
    IMAGE,
    @JsonProperty("branching")
    BRANCHING
}