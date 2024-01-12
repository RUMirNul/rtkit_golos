package com.rtkit.golos.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionid", nullable = false)
    private Integer id;

    @Column(name = "content", nullable = false, length = 300)
    private String content;

    @Column(name = "isanswerrequired", nullable = false)
    private Boolean isAnswerRequired = false;

}