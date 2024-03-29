package com.project.treasurerproject.service;

import com.project.treasurerproject.entity.Tracking;
import com.project.treasurerproject.entity.request.TrackingRequest;
import com.project.treasurerproject.entity.response.TrackingResponse;

import java.util.List;
import java.util.Optional;

public interface TrackingService {
    TrackingResponse create(TrackingRequest trackingRequest);
    TrackingResponse getById(String id);
    Tracking usualGetById(String id);
    List<TrackingResponse> getAll();
    List<TrackingResponse> getByAccumulationId(String id);
    List<TrackingResponse> getByMemberId(String id);
    Optional<Tracking> getByAccumulationAndMemberId(String accumulationId, String memberId);
    TrackingResponse update(TrackingRequest trackingRequest);
    TrackingResponse updateDetail(Tracking tracking);
    String deactivate(String id);

}
