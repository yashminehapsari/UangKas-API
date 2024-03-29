package com.project.treasurerproject.entity.response;

import com.project.treasurerproject.entity.Tracking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private String id;
    private TrackingResponse trackingResponse;
    private Long amount;
    private Integer addition;
    private LocalDate paymentDate;
}
