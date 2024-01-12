package com.rtkit.golos.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "textanswer")
public class TextAnswer {
    @Id
    @Column(name = "answerid", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "answerid", nullable = false)
    private Answer answer;

    @Column(name = "content", length = 320)
    private String content;

}