package dev.yassiraitelghari.fms.repository;

import dev.yassiraitelghari.fms.domain.food.Food;
import dev.yassiraitelghari.fms.domain.supply.SupplierInventory;
import dev.yassiraitelghari.fms.domain.user.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SupplierInventoryRepository extends JpaRepository<SupplierInventory , UUID> {
    List<SupplierInventory> findSupplierInventoriesBySupplier(Supplier supplier);
    List<SupplierInventory> findSupplierInventoriesByFood(Food food);
}
