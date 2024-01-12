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

    @MapsId("pollquestionid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pollquestionid", nullable = false)
    private PollQuestion pollQuestionId;

    @MapsId("answerid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "answerid", nullable = false)
    private Answer answerId;

    @Column(name = "answerorder", nullable = false)
    private Short answerOrder;

}