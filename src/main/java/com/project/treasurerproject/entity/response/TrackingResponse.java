package com.project.treasurerproject.entity.response;

import com.project.treasurerproject.entity.Accumulation;
import com.project.treasurerproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TrackingResponse {
    private String id;
    private Accumulation accumulation;
    private Member member;
    private Integer paid;
    private Long saved;
}
