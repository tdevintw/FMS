package dev.yassiraitelghari.fms.repository;

import dev.yassiraitelghari.fms.domain.supply.SupplierInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierInventoryRepository extends JpaRepository<SupplierInventory , UUID> {

}
