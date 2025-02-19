package dev.yassiraitelghari.fms.repository;

import dev.yassiraitelghari.fms.domain.food.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<Category , UUID> {
}
