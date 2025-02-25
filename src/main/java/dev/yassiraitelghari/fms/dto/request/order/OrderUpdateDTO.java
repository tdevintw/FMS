package dev.yassiraitelghari.fms.dto.request.order;

import dev.yassiraitelghari.fms.domain.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateDTO extends OrderCreateDTO {

    @NotNull(message = "Order status is required.")
    private OrderStatus status;
}
