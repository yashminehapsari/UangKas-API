package com.project.treasurerproject.service;

import com.project.treasurerproject.constant.ERole;
import com.project.treasurerproject.entity.Role;

public interface RoleService {
    Role getOrSave(ERole role);
}
