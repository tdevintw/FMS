package dev.yassiraitelghari.fms.service.user;

import dev.yassiraitelghari.fms.domain.enums.Role;
import dev.yassiraitelghari.fms.domain.user.Manager;
import dev.yassiraitelghari.fms.domain.user.User;
import dev.yassiraitelghari.fms.exception.BuildingManagerIdException;
import dev.yassiraitelghari.fms.exception.UserUUIDNotFound;
import dev.yassiraitelghari.fms.repository.ManagerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ManagerService {
    private final ManagerRepository managerRepository;


    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public Manager findById(UUID id){
        return managerRepository.findById(id).orElseThrow(()->new UserUUIDNotFound("Manager UUID Not Found , UUID is not a manager id or it doesn't exist !"));
    }

    @Transactional
    public void add(User user) {
        Manager manager = new Manager();
        manager.setEmail(user.getEmail());
        manager.setUsername(user.getUsername());
        manager.setPassword(user.getPassword());
        manager.setRole(user.getRole());
        manager.setCreatedAt(user.getCreatedAt());
        manager.setUpdateDate(user.getUpdateDate());
        manager.setVerificationToken(user.getVerificationToken());

        managerRepository.save(manager);
    }

}
