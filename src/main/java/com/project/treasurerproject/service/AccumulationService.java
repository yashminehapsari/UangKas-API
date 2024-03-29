package com.project.treasurerproject.service;


import com.project.treasurerproject.entity.Accumulation;
import com.project.treasurerproject.entity.Period;
import com.project.treasurerproject.entity.request.AccumulationRequest;
import com.project.treasurerproject.entity.response.AccumulationResponse;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AccumulationService {
    AccumulationResponse create(AccumulationRequest accumulationRequest);
    Accumulation usualGetById(String id);
    AccumulationResponse getById(String id);
    List<AccumulationResponse> getAll();
    AccumulationResponse update(AccumulationRequest accumulationRequest);
    String deactivate(String id);
    Integer paymentCount(LocalDate start, LocalDate end, Period period);

}
