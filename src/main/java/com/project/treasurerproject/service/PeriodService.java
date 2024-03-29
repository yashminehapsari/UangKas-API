package com.project.treasurerproject.service;


import com.project.treasurerproject.entity.Period;
import com.project.treasurerproject.entity.request.PeriodRequest;

import java.util.List;

public interface PeriodService {
    Period create(PeriodRequest periodRequest);
    Period getById(String id);
    List<Period> getAll();
    Period update(PeriodRequest periodRequest);
    String deactivate(String id);

}
