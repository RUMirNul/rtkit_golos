package com.rtkit.golos.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "questionanswer")
public class QuestionAnswer {
    @EmbeddedId
    private QuestionAnswerId id;

    @Column(name = "answerorder", nullable = false)
    private Short answerOrder;

}