package dev.yassiraitelghari.fms.dto.response.shipment;

import dev.yassiraitelghari.fms.dto.response.order.OrderDTO;
import dev.yassiraitelghari.fms.dto.response.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentDTO {
    protected UUID id ;
    protected String currentLocation;
    protected UserDTO shipper;
    protected OrderDTO order;

}
