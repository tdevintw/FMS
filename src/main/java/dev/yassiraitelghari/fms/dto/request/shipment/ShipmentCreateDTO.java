package dev.yassiraitelghari.fms.dto.request.shipment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentCreateDTO {

    @NotBlank(message = "Current location is required.")
    protected String currentLocation;

    @NotNull(message = "Shipper ID is required.")
    protected UUID shipperId;

    @NotNull(message = "Order ID is required.")
    protected UUID orderId;
}
