package com.project.treasurerproject.service.impl;

import com.project.treasurerproject.constant.Periods;
import com.project.treasurerproject.entity.Accumulation;
import com.project.treasurerproject.entity.Member;
import com.project.treasurerproject.entity.Period;
import com.project.treasurerproject.entity.UserCredential;
import com.project.treasurerproject.repository.AccumulationRepo;
import com.project.treasurerproject.service.AccumulationService;
import com.project.treasurerproject.service.MemberService;
import com.project.treasurerproject.service.PeriodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;


class AccumulationServiceImplTest {
    private final AccumulationRepo accumulationRepo = mock(AccumulationRepo.class);
    private final PeriodService periodService = mock(PeriodService.class);
    private final MemberService memberService = mock(MemberService.class);
    private final AccumulationService accumulationService = new AccumulationServiceImpl(accumulationRepo,periodService,memberService);

    @BeforeEach
    public void setUp(){
        reset(accumulationRepo,periodService,memberService);
    }

    @Test
    void create() {
        Member dummyMember = new Member("1","name","position","phone","address",true,new UserCredential());
        Period dummyPeriod = new Period("1", Periods.WEEKLY,7,true);
        Accumulation dummyAccumulation = new Accumulation("id","title","goal", LocalDate.of(2024,1,1),LocalDate.of(2024,3,1),dummyPeriod,1000000L,5000L,null,dummyMember,true);

    }

    @Test
    void usualGetById() {
    }

    @Test
    void getById() {
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }

    @Test
    void deactivate() {
    }

    @Test
    void paymentCount() {
    }
}