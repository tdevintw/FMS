package dev.yassiraitelghari.fms.repository;

import dev.yassiraitelghari.fms.domain.user.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier , UUID> {
}
