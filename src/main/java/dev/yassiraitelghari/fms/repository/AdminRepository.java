package dev.yassiraitelghari.fms.repository;

import dev.yassiraitelghari.fms.domain.user.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin , UUID> {
}
