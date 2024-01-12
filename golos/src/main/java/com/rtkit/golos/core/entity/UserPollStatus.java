package com.rtkit.golos.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "userpollstatus")
public class UserPollStatus {
    @Id
    @Column(name = "status", nullable = false, length = 30)
    private String status;
}