package com.project.treasurerproject.entity.response;

import com.project.treasurerproject.entity.Member;
import com.project.treasurerproject.entity.Period;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AccumulationResponse {
    private String id;
    private String title;
    private String goal;
    private Member personInCharge;
    private LocalDate startDate;
    private LocalDate endDate;
    private Period period;
    private Long targetAmount;
    private Long payment;
    private Integer times;
}
