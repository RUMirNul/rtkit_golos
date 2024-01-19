package com.rtkit.golos.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "userpollresult")
public class UserPollResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "userid", nullable = false)
    private GolosUser userId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "pollid", nullable = false)
    private Poll pollId;

    @CreationTimestamp
    @Column(name = "starteddt", nullable = false)
    private Instant startedDt;

    @Column(name = "endeddt")
    private Instant endedDt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserPollStatus status;
}