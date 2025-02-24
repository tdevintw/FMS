package dev.yassiraitelghari.fms.dto.response.payment;

import dev.yassiraitelghari.fms.dto.response.order.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailDTO extends PaymentDTO{
    private OrderDTO order;
}
