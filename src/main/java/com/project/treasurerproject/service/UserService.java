package com.project.treasurerproject.service;

import com.project.treasurerproject.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserByUserId (String id);
}
