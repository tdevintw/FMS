package dev.yassiraitelghari.fms.dto.request.payment;

import jakarta.validation.constraints.Min;
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
public class PaymentCreateDTO {

    @Min(value = 0, message = "Total price must be at least 0.")
    protected double totalPrice;

    @NotNull(message = "Order ID is required.")
    private UUID OrderId;
}
