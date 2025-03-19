package dev.yassiraitelghari.fms.service.user;

import dev.yassiraitelghari.fms.domain.user.Manager;
import dev.yassiraitelghari.fms.domain.user.Shipper;
import dev.yassiraitelghari.fms.domain.user.Supplier;
import dev.yassiraitelghari.fms.domain.user.User;
import dev.yassiraitelghari.fms.dto.request.user.UserUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.user.ShipperDTO;
import dev.yassiraitelghari.fms.dto.response.user.SupplierDTO;
import dev.yassiraitelghari.fms.exception.UserUUIDNotFound;
import dev.yassiraitelghari.fms.exception.UsernameAlreadyExistsException;
import dev.yassiraitelghari.fms.mapper.UserMapper;
import dev.yassiraitelghari.fms.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private UserService userService;

    public SupplierService(SupplierRepository supplierRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.supplierRepository = supplierRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Lazy
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
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

    public void delete(UUID id) {
        Supplier supplier = getById(id);
        supplierRepository.deleteById(id);
    }

    public List<SupplierDTO> getAll() {
        return supplierRepository.findAll().stream().map(userMapper::supplierToSupplierDTO).collect(Collectors.toList());
    }

    public SupplierDTO update(UUID id, UserUpdateDTO user) {


        Supplier supplier = getById(id);

        if(!supplier.getPassword().isEmpty()){
            supplier.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        supplierRepository.save(supplier);
        return userMapper.supplierToSupplierDTO(supplier);
    }




}
