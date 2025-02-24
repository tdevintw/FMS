package dev.yassiraitelghari.fms.service.supply;

import dev.yassiraitelghari.fms.domain.food.Food;
import dev.yassiraitelghari.fms.domain.supply.Order;
import dev.yassiraitelghari.fms.domain.supply.SupplierInventory;
import dev.yassiraitelghari.fms.dto.request.supplierInventory.SupplierInventoryCreateDTO;
import dev.yassiraitelghari.fms.dto.request.supplierInventory.SupplierInventoryUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.supplierInventory.SupplierInventoryDTO;
import dev.yassiraitelghari.fms.dto.response.supplierInventory.SupplierInventoryDetailDTO;
import dev.yassiraitelghari.fms.exception.SupplierInventoryUUIDNotFoundException;
import dev.yassiraitelghari.fms.mapper.SupplierInventoryMapper;
import dev.yassiraitelghari.fms.repository.SupplierInventoryRepository;
import dev.yassiraitelghari.fms.service.food.FoodService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SupplierInventoryService {
    private SupplierInventoryRepository supplierInventoryRepository;
    private SupplierInventoryMapper supplierInventoryMapper;
    private FoodService foodService;

    public SupplierInventoryService(SupplierInventoryRepository supplierInventoryRepository, SupplierInventoryMapper supplierInventoryMapper, FoodService foodService) {
        this.supplierInventoryRepository = supplierInventoryRepository;
        this.supplierInventoryMapper = supplierInventoryMapper;
        this.foodService = foodService;
    }

    public List<SupplierInventoryDetailDTO> getAll() {
        List<SupplierInventory> supplierInventories = supplierInventoryRepository.findAll();
        return supplierInventories.stream().map(supplierInventoryMapper::supplierInventoryToSupplierInventoryDetailDTO).collect(Collectors.toList());
    }

    public SupplierInventoryDetailDTO findById(UUID id) {
        SupplierInventory supplierInventory = this.getById(id);
        return supplierInventoryMapper.supplierInventoryToSupplierInventoryDetailDTO(supplierInventory);
    }

    public SupplierInventory getById(UUID id) {
        return supplierInventoryRepository.findById(id).orElseThrow(() -> new SupplierInventoryUUIDNotFoundException("SupplierInventory UUID not found"));
    }

    public SupplierInventoryDTO add(SupplierInventoryCreateDTO supplierInventory) {
        SupplierInventory newSupplierInventory = supplierInventoryMapper.supplierInventoryCreateDTOToSupplierInventory(supplierInventory);
        return supplierInventoryMapper.supplierInventoryToSupplierInventoryDTO(supplierInventoryRepository.save(newSupplierInventory));
    }

    public SupplierInventoryDetailDTO edit(UUID id, SupplierInventoryUpdateDTO supplierInventory) {
        SupplierInventory updatedSupplierInventory = this.getById(id);
        Food food = foodService.getById(supplierInventory.getFoodId());
        updatedSupplierInventory.setPrice(supplierInventory.getPrice());
        updatedSupplierInventory.setFood(food);
        supplierInventoryRepository.save(updatedSupplierInventory);
        return supplierInventoryMapper.supplierInventoryToSupplierInventoryDetailDTO(updatedSupplierInventory);
    }


    public SupplierInventory edit(SupplierInventory supplierInventory) {
        return supplierInventoryRepository.save(supplierInventory);
    }

    public void delete(UUID id) {
        SupplierInventory supplierInventory = this.getById(id);
        supplierInventoryRepository.deleteById(supplierInventory.getId());
    }

}
