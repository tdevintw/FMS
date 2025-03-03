package dev.yassiraitelghari.fms.service.user;

import dev.yassiraitelghari.fms.domain.user.Manager;
import dev.yassiraitelghari.fms.domain.user.Supplier;
import dev.yassiraitelghari.fms.domain.user.User;
import dev.yassiraitelghari.fms.dto.response.user.SupplierDTO;
import dev.yassiraitelghari.fms.exception.UserUUIDNotFound;
import dev.yassiraitelghari.fms.mapper.UserMapper;
import dev.yassiraitelghari.fms.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final UserMapper userMapper;

    public SupplierService(SupplierRepository supplierRepository, UserMapper userMapper) {
        this.supplierRepository = supplierRepository;
        this.userMapper = userMapper;
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

    public SupplierDTO findById(UUID id) {
        return userMapper.supplierToSupplierDTO(getById(id));
    }

    public void delete(UUID id){
        Supplier supplier = getById(id);
        supplierRepository.deleteById(id);
    }

    public List<SupplierDTO> getAll(){
        return supplierRepository.findAll().stream().map(userMapper::supplierToSupplierDTO).collect(Collectors.toList());
    }

}
