package com.project.treasurerproject.repository;

import com.project.treasurerproject.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepo extends JpaRepository<UserCredential,String> {

    Optional<UserCredential> findByUsername(String username);
}
