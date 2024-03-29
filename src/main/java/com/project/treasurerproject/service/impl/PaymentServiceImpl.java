package com.project.treasurerproject.service.impl;

import com.project.treasurerproject.entity.Payment;
import com.project.treasurerproject.entity.Tracking;
import com.project.treasurerproject.entity.request.PaymentRequest;
import com.project.treasurerproject.entity.request.TrackingRequest;
import com.project.treasurerproject.entity.response.PaymentResponse;
import com.project.treasurerproject.entity.response.TrackingResponse;
import com.project.treasurerproject.repository.PaymentRepo;
import com.project.treasurerproject.service.PaymentService;
import com.project.treasurerproject.service.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepo paymentRepo;
    private final TrackingService trackingService;

    private static TrackingResponse getTrackingResponse(Tracking tracking) {
        return TrackingResponse.builder()
                .id(tracking.getId())
                .accumulation(tracking.getAccumulation())
                .member(tracking.getMember())
                .paid(tracking.getPaid())
                .saved(tracking.getSaved())
                .build();
    }

    @Override
    public PaymentResponse create(PaymentRequest paymentRequest) {
        Tracking tracking = trackingService.usualGetById(paymentRequest.getTrackingId());
        Long[] result = countTimes(paymentRequest.getAmount(), tracking);
        tracking.setSaved(result[0]);
        tracking.setPaid((int) (tracking.getPaid()+result[1]));
        TrackingResponse trackingResponse = trackingService.updateDetail(tracking);
        Payment payment = paymentRepo.save(Payment.builder()
                .tracking(tracking)
                .amount(paymentRequest.getAmount())
                .addition(Math.toIntExact(result[1]))
                .paymentDate(LocalDate.now())
                .build());
        return PaymentResponse.builder()
                .id(payment.getId())
                .trackingResponse(trackingResponse)
                .amount(payment.getAmount())
                .addition(payment.getAddition())
                .paymentDate(payment.getPaymentDate())
                .build();
    }

    @Override
    public PaymentResponse getById(String id) {
        Payment payment = paymentRepo.findById(id).orElse(null);
        if (payment!=null) {
            return getPaymentResponse(payment);
        }
        return null;
    }

    private static PaymentResponse getPaymentResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .trackingResponse(getTrackingResponse(payment.getTracking()))
                .amount(payment.getAmount())
                .addition(payment.getAddition())
                .paymentDate(payment.getPaymentDate())
                .build();
    }

    @Override
    public List<PaymentResponse> getAll() {
        List<Payment> paymentList = paymentRepo.findAll();
        List<PaymentResponse> paymentResponses = new ArrayList<>();
        for (Payment payment :paymentList) {
            paymentResponses.add(getPaymentResponse(payment));
        }
        return paymentResponses;
    }

    @Override
    public Long[] countTimes(Long amount, Tracking tracking) {
        long saved = (amount+tracking.getSaved()) % tracking.getAccumulation().getPayment();
        long addition = (amount+tracking.getSaved()) / tracking.getAccumulation().getPayment();
        return new Long[]{saved, addition};
    }
}
