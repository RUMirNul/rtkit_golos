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
@Table(name = "invite")
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "pollid", nullable = false)
    private Poll pollId;

    @CreationTimestamp
    @Column(name = "createddt", nullable = false)
    private Instant createdDt;

    @CreationTimestamp
    @Column(name = "expiredt")
    private Instant expireDt;

    @Column(name = "uses", nullable = false)
    private Integer uses;

    @Column(name = "maxuses", nullable = false)
    private Integer maxUses;
}
