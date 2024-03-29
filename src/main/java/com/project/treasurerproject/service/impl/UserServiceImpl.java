package com.project.treasurerproject.service.impl;

import com.project.treasurerproject.entity.AppUser;
import com.project.treasurerproject.entity.UserCredential;
import com.project.treasurerproject.repository.UserCredentialRepo;
import com.project.treasurerproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserCredentialRepo userCredentialRepo;
    @Override
    public AppUser loadUserByUserId(String id) {
        UserCredential userCredential = userCredentialRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("invalid credential"));
        return AppUser.builder()
                .id(userCredential.getId())
                .username(userCredential.getUsername())
                .password(userCredential.getPassword())
                .role(userCredential.getRole())
                .build();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserCredential userCredential = userCredentialRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("invalid credential"));
        return AppUser.builder()
                .id(userCredential.getId())
                .username(userCredential.getUsername())
                .password(userCredential.getPassword())
                .role(userCredential.getRole())
                .build();
    }
}
