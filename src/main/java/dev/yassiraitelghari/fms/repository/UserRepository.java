package dev.yassiraitelghari.fms.repository;

import dev.yassiraitelghari.fms.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User , UUID> {

}
