package com.project.treasurerproject.service;

import com.project.treasurerproject.entity.Admin;
import org.springframework.data.domain.Page;

public interface AdminService {
    Admin create(Admin admin);
    Admin getById(String id);
    Page<Admin> getAll(Integer page, Integer size);
    Admin update(Admin admin);
    String deleteById(String id);

}
