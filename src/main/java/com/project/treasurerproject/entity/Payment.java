package com.project.treasurerproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "t_payment")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "tracking_id", nullable = false)
    private Tracking tracking;
    @Column(nullable = false, columnDefinition = "bigint check(amount>0)")
    private Long amount;
    @Column(columnDefinition = "int check(addition>=0)")
    private Integer addition;
    @Column(name = "payment_date")
    private LocalDate paymentDate;
}
