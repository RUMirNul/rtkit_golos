package com.rtkit.golos.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "useranswer")
public class UserAnswer {
    @EmbeddedId
    private UserAnswerId id;

    @Column(name = "useranswer", length = 320)
    private String userAnswer;
}