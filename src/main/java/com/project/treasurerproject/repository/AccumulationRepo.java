package com.project.treasurerproject.repository;

import com.project.treasurerproject.entity.Accumulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccumulationRepo extends JpaRepository<Accumulation,String> {
}
