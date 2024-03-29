package com.project.treasurerproject.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {
    private String id;
    private String name;
    private String position;
    private String phone;
    private String address;
}
