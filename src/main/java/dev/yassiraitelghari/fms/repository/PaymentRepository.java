package dev.yassiraitelghari.fms.repository;

import dev.yassiraitelghari.fms.domain.supply.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface PaymentRepository extends JpaRepository<Payment , UUID> {
}
