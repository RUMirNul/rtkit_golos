package com.rtkit.golos.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class QuestionAnswerId implements Serializable {
    @Column(name = "pollquestionid", nullable = false)
    private Integer pollQuestionId;

    @Column(name = "answerid", nullable = false)
    private Integer answerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        QuestionAnswerId entity = (QuestionAnswerId) o;
        return Objects.equals(this.answerId, entity.answerId) &&
                Objects.equals(this.pollQuestionId, entity.pollQuestionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerId, pollQuestionId);
    }

}