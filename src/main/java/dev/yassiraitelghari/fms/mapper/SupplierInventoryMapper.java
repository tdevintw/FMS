package dev.yassiraitelghari.fms.mapper;

import dev.yassiraitelghari.fms.domain.supply.SupplierInventory;
import dev.yassiraitelghari.fms.dto.request.supplierInventory.SupplierInventoryCreateDTO;
import dev.yassiraitelghari.fms.dto.request.supplierInventory.SupplierInventoryUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.supplierInventory.SupplierInventoryDTO;
import dev.yassiraitelghari.fms.dto.response.supplierInventory.SupplierInventoryDetailDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface SupplierInventoryMapper {
    SupplierInventory supplierInventoryCreateDTOToSupplierInventory(SupplierInventoryCreateDTO supplierInventory);
    SupplierInventoryDTO supplierInventoryToSupplierInventoryDTO(SupplierInventory supplierInventory);
    SupplierInventoryDetailDTO supplierInventoryToSupplierInventoryDetailDTO(SupplierInventory supplierInventory);
    SupplierInventory supplierInventoryUpdateDTOToSupplierInventory(SupplierInventoryUpdateDTO supplierInventory);
}
