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
@Table(name = "poll")
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pollid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "authorid", nullable = false)
    private GolosUser authorId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", nullable = false, length = 300)
    private String description;

    @CreationTimestamp
    @Column(name = "createddt", nullable = false)
    private Instant createdDt;

    @Enumerated(EnumType.STRING)
    @Column(name = "pollstatus")
    private PollStatus status;

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdDt=" + createdDt +
                ", status=" + status +
                '}';
    }
}