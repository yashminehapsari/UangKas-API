package com.project.treasurerproject.service.impl;

import com.project.treasurerproject.constant.ERole;
import com.project.treasurerproject.entity.Role;
import com.project.treasurerproject.repository.RoleRepo;
import com.project.treasurerproject.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;
    @Override
    public Role getOrSave(ERole role) {
        Optional<Role> optionalRole = roleRepo.findByName(role);

        if(optionalRole.isPresent()){
            return optionalRole.get();
        }
        Role currentRole = Role.builder()
                .name(role)
                .build();
        return roleRepo.saveAndFlush(currentRole);
    }
}
