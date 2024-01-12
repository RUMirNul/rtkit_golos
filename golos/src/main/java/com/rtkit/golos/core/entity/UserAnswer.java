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

    @MapsId("pollquestionid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pollquestionid", nullable = false)
    private PollQuestion pollQuestionId;

    @MapsId("answerid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "answerid", nullable = false)
    private Answer answerId;

    @Column(name = "useranswer", length = 320)
    private String userAnswer;
}