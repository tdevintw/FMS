package dev.yassiraitelghari.fms.repository;

import dev.yassiraitelghari.fms.domain.building.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BuildingRepository extends JpaRepository<Building , UUID> {
}
