package dev.yassiraitelghari.fms.dto.response.shipment;

import dev.yassiraitelghari.fms.dto.response.order.OrderDTO;
import dev.yassiraitelghari.fms.dto.response.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ShipmentDetailDTO extends ShipmentDTO{
    private  UserDTO shipper;
    private OrderDTO order;
}
