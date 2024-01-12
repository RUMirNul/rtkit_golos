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
public class FileQuestionId implements Serializable {
    @Column(name = "questionid", nullable = false)
    private Integer questionId;

    @Column(name = "filepath", nullable = false, length = 150)
    private String filePath;

    @Column(name = "filename", nullable = false, length = 50)
    private String fileName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FileQuestionId entity = (FileQuestionId) o;
        return Objects.equals(this.questionId, entity.questionId) &&
                Objects.equals(this.fileName, entity.fileName) &&
                Objects.equals(this.filePath, entity.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, fileName, filePath);
    }

}