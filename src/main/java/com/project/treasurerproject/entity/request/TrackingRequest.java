package com.project.treasurerproject.entity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TrackingRequest {
    private String id;
    private String accumulationId;
    private String memberId;
    private Integer paid;
    private Long saved;
}
