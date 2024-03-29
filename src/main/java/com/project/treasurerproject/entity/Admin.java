package com.project.treasurerproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder(toBuilder = true)
@Table(name = "m_admin")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "phone_number", nullable = false,unique = true)
    private String phoneNumber;
    private Boolean status;
    @OneToOne
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredential;
}
