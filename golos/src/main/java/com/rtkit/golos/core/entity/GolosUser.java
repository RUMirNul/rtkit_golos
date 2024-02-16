package com.rtkit.golos.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "golosuser")
public class GolosUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "firstname", nullable = false, length = 30)
    private String firstName;

    @Column(name = "lastname", nullable = false, length = 30)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false, length = 320)
    private String email;

    @Column(name = "passhash", nullable = false, length = 320)
    private String passHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    @Override
    public String toString() {
        return "GolosUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", passHash='" + passHash + '\'' +
                ", role=" + role +
                '}';
    }
}