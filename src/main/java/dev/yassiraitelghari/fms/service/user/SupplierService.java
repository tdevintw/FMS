package dev.yassiraitelghari.fms.service.user;

import dev.yassiraitelghari.fms.domain.user.Manager;
import dev.yassiraitelghari.fms.domain.user.Supplier;
import dev.yassiraitelghari.fms.domain.user.User;
import dev.yassiraitelghari.fms.exception.UserUUIDNotFound;
import dev.yassiraitelghari.fms.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }


    @Transactional
    public void add(User user) {
        Supplier supplier = new Supplier();
        supplier.setEmail(user.getEmail());
        supplier.setUsername(user.getUsername());
        supplier.setPassword(user.getPassword());
        supplier.setRole(user.getRole());
        supplier.setCreatedAt(user.getCreatedAt());
        supplier.setUpdateDate(user.getUpdateDate());
        supplier.setVerificationToken(user.getVerificationToken());
        supplier.setVerified(true);

        supplierRepository.save(supplier);
    }

    public Supplier getById(UUID id) {
        return supplierRepository.findById(id).orElseThrow(() -> new UserUUIDNotFound("Supplier UUID Not Found"));
    }

}
