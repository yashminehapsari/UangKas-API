package com.project.treasurerproject.repository;

import com.project.treasurerproject.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,String> {
}
