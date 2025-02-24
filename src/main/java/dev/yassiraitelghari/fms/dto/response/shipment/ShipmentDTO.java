package dev.yassiraitelghari.fms.dto.response.shipment;

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
    protected String currentLocation;
    protected UUID shipperId;
    protected UUID orderId;

}
