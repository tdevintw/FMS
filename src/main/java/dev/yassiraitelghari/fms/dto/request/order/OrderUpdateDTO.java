package dev.yassiraitelghari.fms.dto.request.order;

import dev.yassiraitelghari.fms.domain.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateDTO extends OrderCreateDTO {

    @Pattern(regexp = "PENDING|DELIVERED|REFUSED|IN_DELIVERY", message = "Status must be PENDING ,DELIVERED, REFUSED or IN_DELIVERY")
    private String status;
}
