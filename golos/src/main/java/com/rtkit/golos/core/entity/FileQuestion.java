package com.rtkit.golos.core.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "filequestion")
public class FileQuestion {
    @EmbeddedId
    private FileQuestionId id;
}