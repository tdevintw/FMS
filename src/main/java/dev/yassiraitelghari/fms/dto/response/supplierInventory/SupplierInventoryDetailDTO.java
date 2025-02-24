package dev.yassiraitelghari.fms.dto.response.supplierInventory;

import dev.yassiraitelghari.fms.dto.response.order.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierInventoryDetailDTO extends SupplierInventoryDTO {
    private List<OrderDTO> orders;
}
