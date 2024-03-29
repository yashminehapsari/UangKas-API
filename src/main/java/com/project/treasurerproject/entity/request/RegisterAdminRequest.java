package com.project.treasurerproject.entity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAdminRequest {
    private String username;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
}
