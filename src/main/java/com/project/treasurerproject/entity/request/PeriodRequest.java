package com.project.treasurerproject.entity.request;

import com.project.treasurerproject.constant.Periods;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PeriodRequest {
    private String id;
    private Periods period;
    private Integer countInDays;
}
