package com.rtkit.golos.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answerid", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AnswerType type;
}