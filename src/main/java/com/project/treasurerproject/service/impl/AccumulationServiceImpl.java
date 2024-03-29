package com.project.treasurerproject.service.impl;

import com.project.treasurerproject.entity.Accumulation;
import com.project.treasurerproject.entity.Member;
import com.project.treasurerproject.entity.Period;
import com.project.treasurerproject.entity.request.AccumulationRequest;
import com.project.treasurerproject.entity.request.MemberRequest;
import com.project.treasurerproject.entity.response.AccumulationResponse;
import com.project.treasurerproject.repository.AccumulationRepo;
import com.project.treasurerproject.service.AccumulationService;
import com.project.treasurerproject.service.MemberService;
import com.project.treasurerproject.service.PeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccumulationServiceImpl implements AccumulationService {
    private final AccumulationRepo accumulationRepo;
    private final PeriodService periodService;
    private final MemberService memberService;

    @Override
    public AccumulationResponse create(AccumulationRequest accumulationRequest) {
        Period period = periodService.getById(accumulationRequest.getPeriodId());
        Member member = memberService.usualGetById(accumulationRequest.getPersonInChargeId());
        if (period!=null&&member!=null) {
            Accumulation accumulation = accumulationRepo.save(getAccumulation(accumulationRequest, period, member));
            return getAccumulationResponse(accumulation);
        }
        return null;
    }

    private Accumulation getAccumulation(AccumulationRequest accumulationRequest, Period period, Member member) {
        return Accumulation.builder()
                .id(accumulationRequest.getId())
                .title(accumulationRequest.getTitle())
                .goal(accumulationRequest.getGoal())
                .startDate(accumulationRequest.getStartDate())
                .endDate(accumulationRequest.getEndDate())
                .period(period)
                .targetAmount(accumulationRequest.getTargetAmount())
                .payment(accumulationRequest.getPayment())
                .times(paymentCount(accumulationRequest.getStartDate(), accumulationRequest.getEndDate(), period))
                .personInChargeId(member)
                .isActive(true)
                .build();
    }

    private static AccumulationResponse getAccumulationResponse(Accumulation accumulation) {
        return AccumulationResponse.builder()
                .id(accumulation.getId())
                .title(accumulation.getTitle())
                .goal(accumulation.getGoal())
                .personInCharge(accumulation.getPersonInChargeId())
                .startDate(accumulation.getStartDate())
                .endDate(accumulation.getEndDate())
                .period(accumulation.getPeriod())
                .targetAmount(accumulation.getTargetAmount())
                .payment(accumulation.getPayment())
                .times(accumulation.getTimes())
                .build();
    }

    @Override
    public Accumulation usualGetById(String id) {
        return accumulationRepo.findById(id).orElse(null);
    }
    @Override
    public AccumulationResponse getById(String id) {
        Accumulation check = usualGetById(id);
        if (check!=null) {
            return getAccumulationResponse(check);
        }
        return null;
    }

    @Override
    public List<AccumulationResponse> getAll() {
        List<Accumulation> accumulationList = accumulationRepo.findAll();
        List<AccumulationResponse> accumulationResponses = new ArrayList<>();
        for (Accumulation accumulation : accumulationList) {
            accumulationResponses.add(getAccumulationResponse(accumulation));
        }
        return accumulationResponses;
    }

    @Override
    public AccumulationResponse update(AccumulationRequest accumulationRequest) {
        Period period = periodService.getById(accumulationRequest.getPeriodId());
        Member member = memberService.usualGetById(accumulationRequest.getPersonInChargeId());
        Accumulation check = usualGetById(accumulationRequest.getId());
        if (check!=null&&period!=null&&member!=null) {
            return getAccumulationResponse(accumulationRepo.save(getAccumulation(accumulationRequest,period,member)));
        }
        return null;
    }

    @Override
    public String deactivate(String id) {
        Accumulation check = usualGetById(id);
        if (check!=null) {
            check.setIsActive(false);
            accumulationRepo.save(check);
            return "deactivated";
        }
        return null;
    }

    @Override
    public Integer paymentCount(LocalDate start, LocalDate end, Period period) {
        Long days = ChronoUnit.DAYS.between(start, end);
        return Math.toIntExact(days / period.getCountInDays());
    }
}
