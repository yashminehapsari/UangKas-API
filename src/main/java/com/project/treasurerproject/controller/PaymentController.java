package com.project.treasurerproject.controller;

import com.project.treasurerproject.constant.AppPath;
import com.project.treasurerproject.entity.request.PaymentRequest;
import com.project.treasurerproject.entity.response.CommonResponse;
import com.project.treasurerproject.entity.response.PaymentResponse;
import com.project.treasurerproject.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.PAYMENT)
public class PaymentController {
    private final PaymentService paymentService;
    @PostMapping(AppPath.CREATE)
    public ResponseEntity<?> create(@RequestBody PaymentRequest paymentRequest) {
        PaymentResponse newPayment= paymentService.create(paymentRequest);
        if (newPayment!=null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(CommonResponse.builder()
                            .data(newPayment)
                            .message("New payment successfully created")
                            .statusCode(HttpStatus.CREATED.value())
                            .build());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(CommonResponse.builder()
                        .message("Matched id not found")
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build());
    }

    @GetMapping(AppPath.GET_BY_ID)
    public ResponseEntity<?> getById(@PathVariable String id) {
        PaymentResponse found = paymentService.getById(id);
        if (found!=null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CommonResponse.builder()
                            .data(found)
                            .message("Matched id found")
                            .statusCode(HttpStatus.OK.value())
                            .build());
        }return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(CommonResponse.builder()
                        .message("Matched id not found")
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .data(paymentService.getAll())
                        .message("Showing all data")
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }
}
