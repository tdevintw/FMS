package dev.yassiraitelghari.fms.dto.request.order;

import dev.yassiraitelghari.fms.domain.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class OrderUpdateDTO extends OrderCreateDTO{
    private OrderStatus status;
}
