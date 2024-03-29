package com.project.treasurerproject.service.impl;

import com.project.treasurerproject.entity.Period;
import com.project.treasurerproject.entity.request.PeriodRequest;
import com.project.treasurerproject.repository.PeriodRepo;
import com.project.treasurerproject.service.PeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PeriodServiceImpl implements PeriodService {
    private final PeriodRepo periodRepo;

    private static Period getPeriod(PeriodRequest periodRequest) {
        return Period.builder()
                .id(periodRequest.getId())
                .period(periodRequest.getPeriod())
                .countInDays(periodRequest.getCountInDays())
                .isActive(true)
                .build();
    }

    @Override
    public Period create(PeriodRequest periodRequest) {
        Period period = getPeriod(periodRequest);
        return periodRepo.save(period);
    }

    @Override
    public Period getById(String id) {
        return periodRepo.findById(id).orElse(null);
    }

    @Override
    public List<Period> getAll() {
        return periodRepo.findAll();
    }

    @Override
    public Period update(PeriodRequest periodRequest) {
        Period check = getById(periodRequest.getId());
        if (check!=null) {
            return periodRepo.save(getPeriod(periodRequest));
        }
        return null;
    }

    @Override
    public String deactivate(String id) {
        Period check = getById(id);
        if (check!=null) {
            check.setIsActive(false);
            periodRepo.save(check);
            return "deactivated";
        }
        return null;
    }
}
