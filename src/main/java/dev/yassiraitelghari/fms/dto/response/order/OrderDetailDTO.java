package dev.yassiraitelghari.fms.dto.response.order;

import dev.yassiraitelghari.fms.domain.enums.OrderStatus;
import dev.yassiraitelghari.fms.dto.response.shipment.ShipmentDTO;
import dev.yassiraitelghari.fms.dto.response.user.ShipperDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO extends OrderDTO{
    private ShipperDTO shipper;
}
