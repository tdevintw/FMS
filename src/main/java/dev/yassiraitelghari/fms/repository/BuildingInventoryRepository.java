package dev.yassiraitelghari.fms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BuildingInventoryRepository extends JpaRepository<BuildingRepository , UUID> {
}
