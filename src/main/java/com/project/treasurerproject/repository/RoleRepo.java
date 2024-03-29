package com.project.treasurerproject.repository;

import com.project.treasurerproject.constant.ERole;
import com.project.treasurerproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,String> {

    Optional<Role> findByName(ERole name);
}
