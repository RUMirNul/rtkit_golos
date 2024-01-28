package com.rtkit.golos.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "usertextanswer")
@ToString
public class UserTextAnswer {
    @Id
    @Column(name = "answerid", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "answerid", nullable = false)
    private Answer answer;

    @Column(name = "preparedtext", length = 320)
    private String preparedText;
}
