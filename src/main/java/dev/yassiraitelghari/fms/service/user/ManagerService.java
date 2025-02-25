package dev.yassiraitelghari.fms.service.user;

import dev.yassiraitelghari.fms.domain.user.Manager;
import dev.yassiraitelghari.fms.exception.UserUUIDNotFound;
import dev.yassiraitelghari.fms.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ManagerService {
    private final ManagerRepository managerRepository;


    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public Manager findById(UUID id){
        return managerRepository.findById(id).orElseThrow(()->new UserUUIDNotFound("User UUID Not Found"));
    }
}
