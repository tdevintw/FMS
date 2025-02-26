package dev.yassiraitelghari.fms.service.user;

import dev.yassiraitelghari.fms.domain.user.Manager;
import dev.yassiraitelghari.fms.domain.user.Shipper;
import dev.yassiraitelghari.fms.domain.user.User;
import dev.yassiraitelghari.fms.repository.ShipperRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShipperService {

    private final ShipperRepository shipperRepository;

    public ShipperService(ShipperRepository shipperRepository) {
        this.shipperRepository = shipperRepository;
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


}
