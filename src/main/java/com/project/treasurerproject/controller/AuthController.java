package com.project.treasurerproject.controller;

import com.project.treasurerproject.constant.AppPath;
import com.project.treasurerproject.entity.request.AuthRequest;
import com.project.treasurerproject.entity.request.RegisterAdminRequest;
import com.project.treasurerproject.entity.request.RegisterMemberRequest;
import com.project.treasurerproject.entity.response.CommonResponse;
import com.project.treasurerproject.entity.response.LoginResponse;
import com.project.treasurerproject.entity.response.RegisterResponse;
import com.project.treasurerproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.AUTH)
public class AuthController {
    private final AuthService authService;

    @PostMapping(AppPath.REGISTER_MEMBER)
    public ResponseEntity<?> registerMember(@RequestBody RegisterMemberRequest request) {
        RegisterResponse registerResponse = authService.registerMember(request);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .message("Successfully registered new member")
                .statusCode(HttpStatus.CREATED.value())
                .data(registerResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @PostMapping(AppPath.REGISTER_ADMIN)
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterAdminRequest request) {
        RegisterResponse registerResponse = authService.registerAdmin(request);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .message("Successfully registered new admin")
                .statusCode(HttpStatus.CREATED.value())
                .data(registerResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping(AppPath.LOGIN)
    public ResponseEntity<?> signIn(@RequestBody AuthRequest request){
        LoginResponse loginResponse = authService.login(request);
        CommonResponse<LoginResponse> response = CommonResponse.<LoginResponse>builder()
                .message("successfully signed in")
                .statusCode(HttpStatus.OK.value())
                .data(loginResponse)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
