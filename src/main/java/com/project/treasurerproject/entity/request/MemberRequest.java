package com.project.treasurerproject.entity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequest {
    private String id;
    private String name;
    private String position;
    private String phone;
    private String address;
}
