package dev.yassiraitelghari.fms.dto.response.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    protected UUID id ;
    private double quantity;
    protected UUID supplierInventoryId;
    protected UUID buildingId;
}
