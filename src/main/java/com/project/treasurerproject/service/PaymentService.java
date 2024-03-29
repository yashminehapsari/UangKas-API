package com.project.treasurerproject.service;


import com.project.treasurerproject.entity.Accumulation;
import com.project.treasurerproject.entity.Payment;
import com.project.treasurerproject.entity.Tracking;
import com.project.treasurerproject.entity.request.PaymentRequest;
import com.project.treasurerproject.entity.response.PaymentResponse;

import java.util.List;

public interface PaymentService {
    PaymentResponse create(PaymentRequest paymentRequest);
    PaymentResponse getById(String id);
    List<PaymentResponse> getAll();
    Long[] countTimes(Long amount, Tracking tracking);
}
