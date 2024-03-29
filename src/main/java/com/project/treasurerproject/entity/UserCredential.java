package com.project.treasurerproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder(toBuilder = true)
@Table(name = "m_user_credential")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCredential {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
