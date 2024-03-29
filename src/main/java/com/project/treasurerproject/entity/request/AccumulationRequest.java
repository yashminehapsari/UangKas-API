package com.project.treasurerproject.entity.request;

import com.project.treasurerproject.constant.Periods;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AccumulationRequest {
    private String id;
    private String title;
    private String goal;
    private LocalDate startDate;
    private LocalDate endDate;
    private String periodId;
    private Long targetAmount;
    private Long payment;
    private String personInChargeId;
}
