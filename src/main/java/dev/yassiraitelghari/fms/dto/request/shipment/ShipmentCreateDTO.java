package dev.yassiraitelghari.fms.dto.request.shipment;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 4 , message = "Location must be contain at least 4 characters")
    protected String currentLocation;

    @NotNull(message = "Shipper ID is required.")
    protected UUID shipperId;

    @NotNull(message = "Order ID is required.")
    protected UUID orderId;
}
