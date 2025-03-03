package dev.yassiraitelghari.fms.dto.response.user;

import dev.yassiraitelghari.fms.dto.response.supplierInventory.SupplierInventoryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class SupplierDTO extends UserDTO{
    private boolean isSupplierRegistered;
    private List<SupplierInventoryDTO> inventories;
}
