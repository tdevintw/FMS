package dev.yassiraitelghari.fms.service.user;

import dev.yassiraitelghari.fms.domain.enums.Role;
import dev.yassiraitelghari.fms.domain.user.Admin;
import dev.yassiraitelghari.fms.domain.user.Manager;
import dev.yassiraitelghari.fms.domain.user.User;
import dev.yassiraitelghari.fms.dto.request.user.UserUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.user.AdminDTO;
import dev.yassiraitelghari.fms.dto.response.user.ManagerDTO;
import dev.yassiraitelghari.fms.exception.BuildingManagerIdException;
import dev.yassiraitelghari.fms.exception.UserUUIDNotFound;
import dev.yassiraitelghari.fms.exception.UsernameAlreadyExistsException;
import dev.yassiraitelghari.fms.mapper.UserMapper;
import dev.yassiraitelghari.fms.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private UserService userService;

    public ManagerService(ManagerRepository managerRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.managerRepository = managerRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    @Lazy
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Manager findById(UUID id) {
        return managerRepository.findById(id).orElseThrow(() -> new UserUUIDNotFound("Manager UUID Not Found , UUID is not a manager id or it doesn't exist !"));
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

    public ManagerDTO getById(UUID id) {
        return userMapper.managerToManagerDTO(findById(id));
    }

    public void delete(UUID id) {
        Manager manager = findById(id);
        managerRepository.deleteById(id);
    }

    public List<ManagerDTO> getAll() {
        List<Manager> managers = managerRepository.findAll();
        return managers.stream().map(userMapper::managerToManagerDTO).collect(Collectors.toList());
    }

    public ManagerDTO update(UUID id, UserUpdateDTO user) {


        Manager manager = findById(id);
        if (!manager.getPassword().isEmpty()) {
            manager.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        managerRepository.save(manager);
        return userMapper.managerToManagerDTO(manager);
    }


}
