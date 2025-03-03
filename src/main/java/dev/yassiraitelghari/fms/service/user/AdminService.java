package dev.yassiraitelghari.fms.service.user;

import dev.yassiraitelghari.fms.domain.user.Admin;
import dev.yassiraitelghari.fms.domain.user.Manager;
import dev.yassiraitelghari.fms.domain.user.User;
import dev.yassiraitelghari.fms.dto.response.user.AdminDTO;
import dev.yassiraitelghari.fms.exception.UserUUIDNotFound;
import dev.yassiraitelghari.fms.mapper.UserMapper;
import dev.yassiraitelghari.fms.repository.AdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdminService {


    private final AdminRepository adminRepository;
    private final UserMapper userMapper;

    public AdminService(AdminRepository adminRepository, UserMapper userMapper) {
        this.adminRepository = adminRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public void add(User user) {
        Admin admin = new Admin();
        admin.setEmail(user.getEmail());
        admin.setUsername(user.getUsername());
        admin.setPassword(user.getPassword());
        admin.setRole(user.getRole());
        admin.setCreatedAt(user.getCreatedAt());
        admin.setUpdateDate(user.getUpdateDate());
        admin.setVerificationToken(user.getVerificationToken());

        adminRepository.save(admin);
    }

    public Admin getById(UUID id) {
        return adminRepository.findById(id).orElseThrow(() -> new UserUUIDNotFound("Admin UUID Not Found"));
    }

    public AdminDTO findById(UUID id) {
        return userMapper.adminToAdminDTO(getById(id));
    }

    public void delete(UUID id) {
        Admin admin = getById(id);
        adminRepository.deleteById(id);
    }

    public List<AdminDTO> getAll() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream().map(userMapper::adminToAdminDTO).collect(Collectors.toList());
    }


}
