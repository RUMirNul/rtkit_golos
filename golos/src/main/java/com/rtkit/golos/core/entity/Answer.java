package com.rtkit.golos.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "answer")
@ToString
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answerid", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AnswerType type;

    @Column(name = "nextquestionid")
    private Integer nextQuestionId;
}