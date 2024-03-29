package com.project.treasurerproject.entity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RegisterMemberRequest {
    private String username;
    private String email;
    private String password;
    private String name;
    private String position;
    private String phone;
    private String address;

}
