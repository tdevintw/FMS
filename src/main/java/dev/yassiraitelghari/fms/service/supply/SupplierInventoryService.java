package dev.yassiraitelghari.fms.service.supply;

import dev.yassiraitelghari.fms.domain.enums.Role;
import dev.yassiraitelghari.fms.domain.food.Food;
import dev.yassiraitelghari.fms.domain.supply.Order;
import dev.yassiraitelghari.fms.domain.supply.SupplierInventory;
import dev.yassiraitelghari.fms.domain.user.Manager;
import dev.yassiraitelghari.fms.domain.user.Supplier;
import dev.yassiraitelghari.fms.domain.user.User;
import dev.yassiraitelghari.fms.dto.request.supplierInventory.SupplierInventoryCreateDTO;
import dev.yassiraitelghari.fms.dto.request.supplierInventory.SupplierInventoryUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.supplierInventory.SupplierInventoryDTO;
import dev.yassiraitelghari.fms.dto.response.supplierInventory.SupplierInventoryDetailDTO;
import dev.yassiraitelghari.fms.exception.NotAuthorizedToAssignBuildingToManagerException;
import dev.yassiraitelghari.fms.exception.SupplierInventoryUUIDNotFoundException;
import dev.yassiraitelghari.fms.exception.isAuthorizedToManageSupplierInventory;
import dev.yassiraitelghari.fms.mapper.SupplierInventoryMapper;
import dev.yassiraitelghari.fms.repository.SupplierInventoryRepository;
import dev.yassiraitelghari.fms.service.food.FoodService;
import dev.yassiraitelghari.fms.service.user.SupplierService;
import dev.yassiraitelghari.fms.service.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SupplierInventoryService {
    private final SupplierInventoryRepository supplierInventoryRepository;
    private final SupplierInventoryMapper supplierInventoryMapper;
    private final FoodService foodService;
    private final SupplierService supplierService;
    private final UserService userService;

    public SupplierInventoryService(SupplierInventoryRepository supplierInventoryRepository, SupplierInventoryMapper supplierInventoryMapper, FoodService foodService, SupplierService supplierService, UserService userService) {
        this.supplierInventoryRepository = supplierInventoryRepository;
        this.supplierInventoryMapper = supplierInventoryMapper;
        this.foodService = foodService;
        this.supplierService = supplierService;

        this.userService = userService;
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

    public SupplierInventoryDTO add(SupplierInventoryCreateDTO supplierInventoryCreateDTO) {
        Food food = foodService.getById(supplierInventoryCreateDTO.getFoodId());
        Supplier supplier = supplierService.getById(supplierInventoryCreateDTO.getSupplierId());
        SupplierInventory supplierInventory = supplierInventoryMapper.supplierInventoryCreateDTOToSupplierInventory(supplierInventoryCreateDTO);
        supplierInventory.setFood(food);
        supplierInventory.setSupplier(supplier);
        return supplierInventoryMapper.supplierInventoryToSupplierInventoryDTO(supplierInventoryRepository.save(supplierInventory));
    }

    public SupplierInventoryDetailDTO edit(UUID id, SupplierInventoryUpdateDTO supplierInventory) {
        SupplierInventory updatedSupplierInventory = this.getById(id);
        Supplier supplier = supplierService.getById(updatedSupplierInventory.getSupplier().getId());
        isAuthorizedToSupplierInventory(supplier);
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
        Supplier supplier = supplierService.getById(supplierInventory.getSupplier().getId());
        isAuthorizedToSupplierInventory(supplier);
        supplierInventoryRepository.deleteById(supplierInventory.getId());
    }

    public void isAuthorizedToSupplierInventory(Supplier user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User authUser = userService.getByUsername(username);
        if (!authUser.getRole().equals(Role.ADMIN) && !authUser.getId().equals(user.getId())) {
            throw new isAuthorizedToManageSupplierInventory("You can't manage inventories");
        }
    }

}
