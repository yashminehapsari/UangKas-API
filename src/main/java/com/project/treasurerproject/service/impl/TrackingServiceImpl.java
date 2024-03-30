package com.project.treasurerproject.service.impl;

import com.project.treasurerproject.entity.Accumulation;
import com.project.treasurerproject.entity.Member;
import com.project.treasurerproject.entity.Tracking;
import com.project.treasurerproject.entity.request.TrackingRequest;
import com.project.treasurerproject.entity.response.TrackingResponse;
import com.project.treasurerproject.repository.TrackingRepo;
import com.project.treasurerproject.service.AccumulationService;
import com.project.treasurerproject.service.MemberService;
import com.project.treasurerproject.service.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrackingServiceImpl implements TrackingService {
    private final TrackingRepo trackingRepo;
    private final MemberService memberService;
    private final AccumulationService accumulationService;

    @Override
    public TrackingResponse create(TrackingRequest trackingRequest) {
        Accumulation accumulation = accumulationService.usualGetById(trackingRequest.getAccumulationId());
        Member member = memberService.usualGetById(trackingRequest.getMemberId());
        if (accumulation!=null&&member!=null) {
            if (getByAccumulationAndMemberId(trackingRequest.getAccumulationId(), trackingRequest.getMemberId()).isEmpty()) {
                Tracking tracking = Tracking.builder()
                        .accumulation(accumulation)
                        .member(member)
                        .paid(0)
                        .saved(0L)
                        .build();;
                return getTrackingResponse(trackingRepo.save(tracking));
            }
        }
        return null;
    }

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
    public Tracking usualGetById(String id) {
        return trackingRepo.findById(id).orElse(null);
    }
    @Override
    public TrackingResponse getById(String id) {
        Tracking tracking = trackingRepo.findById(id).orElse(null);
        if (tracking!=null) {
            return getTrackingResponse(tracking);
        }
        return null;
    }

    @Override
    public List<TrackingResponse> getByAccumulationId(String id) {
        Accumulation check = accumulationService.usualGetById(id);
        if (check!=null) {
            List<Tracking> trackingList = trackingRepo.findByAccumulationId(id);
            List<TrackingResponse> trackingResponses = new ArrayList<>();
            for (Tracking tracking : trackingList) {
                trackingResponses.add(getTrackingResponse(tracking));
            }
            return trackingResponses;
        }
        return null;
    }

    @Override
    public List<TrackingResponse> getByMemberId(String id) {
        Member check = memberService.usualGetById(id);
        if (check!=null) {
            List<Tracking> trackingList = trackingRepo.findByMemberId(id);
            List<TrackingResponse> trackingResponses = new ArrayList<>();
            for (Tracking tracking : trackingList) {
                trackingResponses.add(getTrackingResponse(tracking));
            }
            return trackingResponses;
        }
        return null;
    }

    @Override
    public Optional<Tracking> getByAccumulationAndMemberId(String accumulationId, String memberId) {
        return trackingRepo.findByAccumulationAndMemberId(accumulationId, memberId);
    }

    @Override
    public List<TrackingResponse> getAll() {
        List<Tracking> trackingList = trackingRepo.findAll();
        List<TrackingResponse> trackingResponses = new ArrayList<>();
        for (Tracking tracking : trackingList) {
            trackingResponses.add(getTrackingResponse(tracking));
        }
        return trackingResponses;
    }

    @Override
    public TrackingResponse update(TrackingRequest trackingRequest) {
        Tracking check = usualGetById(trackingRequest.getId());
        if (check!=null) {
            return getTrackingResponse(trackingRepo.save(Tracking.builder()
                    .id(trackingRequest.getId())
                    .accumulation(check.getAccumulation())
                    .member(check.getMember())
                    .paid(trackingRequest.getPaid())
                    .saved(trackingRequest.getSaved())
                    .build()));
        }
        return null;
    }

    @Override
    public TrackingResponse updateDetail(Tracking tracking) {
        Tracking check = usualGetById(tracking.getId());
        if (check!=null) {
            return getTrackingResponse(trackingRepo.save(Tracking.builder()
                    .id(tracking.getId())
                    .accumulation(tracking.getAccumulation())
                    .member(tracking.getMember())
                    .paid(tracking.getPaid())
                    .saved(tracking.getSaved())
                    .build()));
        }
        return null;
    }

    @Override
    public String deactivate(String id) {
        Tracking check = usualGetById(id);
        if (check!=null) {
            check.setIsActive(false);
            trackingRepo.save(check);
            return "deactivated";
        }return null;
    }
}
