package dev.yassiraitelghari.fms.repository;

import dev.yassiraitelghari.fms.domain.location.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CountryRepository extends JpaRepository<Country , UUID> {
}
