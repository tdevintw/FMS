package dev.yassiraitelghari.fms.repository;

import dev.yassiraitelghari.fms.domain.user.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper , UUID> {
}
