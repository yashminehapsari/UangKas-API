package com.project.treasurerproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_tracking")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Tracking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "accumulation_id", nullable = false)
    private Accumulation accumulation;
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    @Column(columnDefinition = "int check(paid>=0)")
    private Integer paid;
    @Column(columnDefinition = "bigint check(saved>=0)")
    private Long saved;
    @Column(name = "is_active")
    private Boolean isActive;
}
