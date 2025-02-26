package dev.yassiraitelghari.fms.service.user;

import dev.yassiraitelghari.fms.domain.user.Admin;
import dev.yassiraitelghari.fms.domain.user.Manager;
import dev.yassiraitelghari.fms.domain.user.User;
import dev.yassiraitelghari.fms.repository.AdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {


    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
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

}
