package com.rtkit.golos.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "answertype")
public class AnswerType {
    @Id
    @Column(name = "type", nullable = false, length = 30)
    private String type;
}