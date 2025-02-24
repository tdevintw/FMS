package dev.yassiraitelghari.fms.repository;

import dev.yassiraitelghari.fms.domain.supply.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ShipmentRepository extends JpaRepository<Shipment , UUID> {
}
