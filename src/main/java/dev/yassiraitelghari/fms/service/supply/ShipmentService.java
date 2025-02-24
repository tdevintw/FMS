package dev.yassiraitelghari.fms.service.supply;

import dev.yassiraitelghari.fms.domain.supply.Order;
import dev.yassiraitelghari.fms.domain.supply.Shipment;
import dev.yassiraitelghari.fms.domain.user.Shipper;
import dev.yassiraitelghari.fms.dto.request.shipment.ShipmentCreateDTO;
import dev.yassiraitelghari.fms.dto.request.shipment.ShipmentUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.shipment.ShipmentDTO;
import dev.yassiraitelghari.fms.dto.response.shipment.ShipmentDetailDTO;
import dev.yassiraitelghari.fms.exception.ShipmentUUIDNotFoundException;
import dev.yassiraitelghari.fms.mapper.ShipmentMapper;
import dev.yassiraitelghari.fms.repository.ShipmentRepository;
import dev.yassiraitelghari.fms.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final ShipmentMapper shipmentMapper;
    private final OrderService orderService;

    public ShipmentService(ShipmentRepository shipmentRepository, ShipmentMapper shipmentMapper, OrderService orderService, UserService userService) {
        this.shipmentRepository = shipmentRepository;
        this.shipmentMapper = shipmentMapper;
        this.orderService = orderService;
    }

    public List<ShipmentDetailDTO> getAll() {
        List<Shipment> shipments = shipmentRepository.findAll();
        return shipments.stream().map(shipmentMapper::shipmentToShipmentDetailDTO).collect(Collectors.toList());
    }

    public ShipmentDetailDTO findById(UUID id) {
        Shipment shipment = this.getById(id);
        return shipmentMapper.shipmentToShipmentDetailDTO(shipment);
    }

    public Shipment getById(UUID id) {
        return shipmentRepository.findById(id).orElseThrow(() -> new ShipmentUUIDNotFoundException("Shipment UUID not found"));
    }

    public ShipmentDTO add(ShipmentCreateDTO shipment) {
        Shipment newShipment = shipmentMapper.shipmentCreateDTOToShipment(shipment);
        return shipmentMapper.shipmentToShipmentDTO(shipmentRepository.save(newShipment));
    }

    public ShipmentDetailDTO edit(UUID id, ShipmentUpdateDTO shipment) {
        Shipment updatedShipment = this.getById(id);
        Order newOrder = orderService.getById(shipment.getOrderId());
        updatedShipment.setOrder(newOrder);
        shipmentRepository.save(updatedShipment);
        return shipmentMapper.shipmentToShipmentDetailDTO(updatedShipment);
    }


    public Shipment edit(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    public void delete(UUID id) {
        Shipment shipment = this.getById(id);
        shipmentRepository.deleteById(shipment.getId());
    }

}
