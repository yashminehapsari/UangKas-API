package com.project.treasurerproject.service;


import com.project.treasurerproject.entity.request.AuthRequest;
import com.project.treasurerproject.entity.request.RegisterAdminRequest;
import com.project.treasurerproject.entity.request.RegisterMemberRequest;
import com.project.treasurerproject.entity.response.LoginResponse;
import com.project.treasurerproject.entity.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerMember(RegisterMemberRequest request);
    RegisterResponse registerAdmin(RegisterAdminRequest request);
    LoginResponse login(AuthRequest authRequest);
}
