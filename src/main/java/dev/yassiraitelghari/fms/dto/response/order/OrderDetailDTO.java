package dev.yassiraitelghari.fms.dto.response.order;

import dev.yassiraitelghari.fms.domain.enums.OrderStatus;
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
    private OrderStatus status;
    private UUID shipmentId;
    private UUID paymentId;
}
