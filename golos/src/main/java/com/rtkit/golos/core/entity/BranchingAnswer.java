package com.rtkit.golos.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "branchinganswer")
public class BranchingAnswer {
    @Id
    @Column(name = "answerid", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "answerid", nullable = false)
    private Answer answer;

    @Column(name = "orderind", nullable = false)
    private Short orderInd;
}