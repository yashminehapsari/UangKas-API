package com.project.treasurerproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "m_accumulation")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Accumulation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String title;
    private String goal;
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "period_id", nullable = false)
    private Period period;
    @Column(name = "target_amount", columnDefinition = "bigint check(target_amount>0)")
    private Long targetAmount;
    @Column(nullable = false, columnDefinition = "bigint check(payment<target_amount and payment>0)")
    private Long payment;
    private Integer times;
    @ManyToOne
    @JoinColumn(name = "pic_id", nullable = false)
    private Member personInChargeId;
    @Column(name = "is_active")
    private Boolean isActive;
}
