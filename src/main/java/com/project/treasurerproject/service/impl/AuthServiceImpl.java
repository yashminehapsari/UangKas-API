package com.project.treasurerproject.service.impl;

import com.project.treasurerproject.constant.ERole;
import com.project.treasurerproject.entity.*;
import com.project.treasurerproject.entity.request.AuthRequest;
import com.project.treasurerproject.entity.request.RegisterAdminRequest;
import com.project.treasurerproject.entity.request.RegisterMemberRequest;
import com.project.treasurerproject.entity.response.LoginResponse;
import com.project.treasurerproject.entity.response.RegisterResponse;
import com.project.treasurerproject.repository.MemberRepo;
import com.project.treasurerproject.repository.UserCredentialRepo;
import com.project.treasurerproject.security.JwtUtil;
import com.project.treasurerproject.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserCredentialRepo userCredentialRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final MemberRepo memberRepo;
    private final AdminService adminService;
    private final RoleService roleService;
    @Override
    @Transactional(rollbackOn = Exception.class)
    public RegisterResponse registerMember(RegisterMemberRequest request) {
        try {
            Role role = roleService.getOrSave(ERole.ROLE_MEMBER);
            UserCredential userCredential = UserCredential.builder()
                    .username(request.getUsername().toLowerCase())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(role)
                    .build();
            userCredentialRepo.saveAndFlush(userCredential);
            Member member = Member.builder()
                    .name(request.getName())
                    .position(request.getPosition())
                    .phone(request.getPhone())
                    .address(request.getAddress())
                    .userCredential(userCredential)
                    .build();
            memberRepo.save(member);
            return RegisterResponse.builder()
                    .email(request.getEmail())
                    .role(userCredential.getRole().getName().toString())
                    .build();
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"user already exist");
        }
    }
    @Override
    @Transactional(rollbackOn = Exception.class)
    public RegisterResponse registerAdmin(RegisterAdminRequest request) {
        try {
            Role role = roleService.getOrSave(ERole.ROLE_ADMIN);
            UserCredential userCredential = UserCredential.builder()
                    .username(request.getUsername().toLowerCase())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(role)
                    .build();
            userCredentialRepo.saveAndFlush(userCredential);
            Admin admin = Admin.builder()
                    .name(request.getName())
                    .phoneNumber(request.getPhoneNumber())
                    .status(true)
                    .userCredential(userCredential)
                    .build();
            adminService.create(admin);
            return RegisterResponse.builder()
                    .email(request.getEmail())
                    .role(userCredential.getRole().getName().name())
                    .build();
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"this admin already exist");
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public LoginResponse login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername().toLowerCase(),authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AppUser appUser = (AppUser) authentication.getPrincipal();
        String token = jwtUtil.generateToken(appUser);
        return LoginResponse.builder()
                .email(appUser.getEmail())
                .token(token)
                .role(appUser.getRole().getName().name())
                .build();
    }
}
