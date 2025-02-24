package dev.yassiraitelghari.fms.dto.request.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDTO {
    protected double quantity;
    private UUID supplierInventoryId;
    private UUID buildingId;



}
