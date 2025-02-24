package dev.yassiraitelghari.fms.mapper;

import dev.yassiraitelghari.fms.domain.supply.Shipment;
import dev.yassiraitelghari.fms.dto.request.shipment.ShipmentCreateDTO;
import dev.yassiraitelghari.fms.dto.request.shipment.ShipmentUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.shipment.ShipmentDTO;
import dev.yassiraitelghari.fms.dto.response.shipment.ShipmentDetailDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ShipmentMapper {
    Shipment shipmentCreateDTOToShipment(ShipmentCreateDTO shipment);

    ShipmentDTO shipmentToShipmentDTO(Shipment shipment);

    ShipmentDetailDTO shipmentToShipmentDetailDTO(Shipment shipment);

    Shipment shipmentUpdateDTOToShipment(ShipmentUpdateDTO shipment);

}
