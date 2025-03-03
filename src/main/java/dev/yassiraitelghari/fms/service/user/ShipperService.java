package dev.yassiraitelghari.fms.service.user;

import dev.yassiraitelghari.fms.domain.user.Manager;
import dev.yassiraitelghari.fms.domain.user.Shipper;
import dev.yassiraitelghari.fms.domain.user.User;
import dev.yassiraitelghari.fms.dto.request.user.UserUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.user.ManagerDTO;
import dev.yassiraitelghari.fms.dto.response.user.ShipperDTO;
import dev.yassiraitelghari.fms.exception.UserUUIDNotFound;
import dev.yassiraitelghari.fms.exception.UsernameAlreadyExistsException;
import dev.yassiraitelghari.fms.mapper.UserMapper;
import dev.yassiraitelghari.fms.repository.ShipperRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShipperService {

    private final ShipperRepository shipperRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public ShipperService(ShipperRepository shipperRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, UserService userService) {
        this.shipperRepository = shipperRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }


    @Transactional
    public void add(User user) {
        Shipper shipper = new Shipper();
        shipper.setEmail(user.getEmail());
        shipper.setUsername(user.getUsername());
        shipper.setPassword(user.getPassword());
        shipper.setRole(user.getRole());
        shipper.setCreatedAt(user.getCreatedAt());
        shipper.setUpdateDate(user.getUpdateDate());
        shipper.setVerificationToken(user.getVerificationToken());

        shipperRepository.save(shipper);
    }

    public Shipper getById(UUID id) {
        return shipperRepository.findById(id).orElseThrow(() -> new UserUUIDNotFound("Shipper UUID Not Found"));
    }

    public ShipperDTO findById(UUID id) {
        return userMapper.shipperToShipperDTO(getById(id));
    }

    public void delete(UUID id) {
        Shipper shipper = getById(id);
        shipperRepository.deleteById(id);
    }

    public List<ShipperDTO> getAll() {
        List<Shipper> shippers = shipperRepository.findAll();
        return shippers.stream().map(userMapper::shipperToShipperDTO).collect(Collectors.toList());
    }

    public ShipperDTO update(UUID id, UserUpdateDTO user) {


        Shipper shipper = getById(id);
        userService.findByUsername(user.getUsername())
                .ifPresent(existingUser -> {
                    throw new UsernameAlreadyExistsException("Username already exists");
                });

        shipper.setUsername(user.getUsername());
        shipper.setPassword(passwordEncoder.encode(user.getPassword()));
        shipperRepository.save(shipper);
        return userMapper.shipperToShipperDTO(shipper);
    }

}
