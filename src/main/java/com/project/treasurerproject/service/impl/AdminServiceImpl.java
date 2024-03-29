package com.project.treasurerproject.service.impl;


import com.project.treasurerproject.entity.Admin;
import com.project.treasurerproject.repository.AdminRepo;
import com.project.treasurerproject.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepo adminRepo;

    @Override
    public Admin create(Admin admin) {
        return adminRepo.save(admin);
    }

    @Override
    public Admin getById(String id) {
        return adminRepo.findById(id).orElse(null);
    }

    @Override
    public Page<Admin> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Admin> admins = adminRepo.findAll(pageable);
        List<Admin> list = new ArrayList<>(admins.getContent());
        return new PageImpl<>(list,pageable, admins.getTotalElements());
    }

    @Override
    public Admin update(Admin admin) {
        Admin old = getById(admin.getId());
        if (old!=null){
            return adminRepo.save(admin);
        }
        return null;
    }

    @Override
    public String deleteById(String id) {
        Admin old = getById(id);
        if (old!=null){
            adminRepo.deleteById(id);
            return "deleted";
        }
        return null;
    }
}
