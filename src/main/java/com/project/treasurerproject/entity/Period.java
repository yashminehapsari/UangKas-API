package com.project.treasurerproject.entity;

import com.project.treasurerproject.constant.Periods;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "m_period")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private Periods period;
    @Column(name = "count_in_days", nullable = false, columnDefinition = "int check(count_in_days>0)")
    private Integer countInDays;
    @Column(name = "is_active")
    private Boolean isActive;
}