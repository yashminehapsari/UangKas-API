package com.project.treasurerproject.repository;

import com.project.treasurerproject.entity.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodRepo extends JpaRepository<Period,String> {
}
