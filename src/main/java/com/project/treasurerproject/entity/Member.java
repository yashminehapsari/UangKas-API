package com.project.treasurerproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "m_member")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String position;
    @Column(unique = true)
    private String phone;
    @Column(nullable = false)
    private String address;
    @Column(name = "is_active")
    private Boolean isActive;
    @OneToOne
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredential;
}


