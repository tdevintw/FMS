package dev.yassiraitelghari.fms.service.supply;

import dev.yassiraitelghari.fms.domain.building.Building;
import dev.yassiraitelghari.fms.domain.enums.OrderStatus;
import dev.yassiraitelghari.fms.domain.supply.Order;
import dev.yassiraitelghari.fms.domain.supply.SupplierInventory;
import dev.yassiraitelghari.fms.dto.request.order.OrderCreateDTO;
import dev.yassiraitelghari.fms.dto.request.order.OrderUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.order.OrderDTO;
import dev.yassiraitelghari.fms.dto.response.order.OrderDetailDTO;
import dev.yassiraitelghari.fms.exception.OrderUUIDNotFoundException;
import dev.yassiraitelghari.fms.mapper.OrderMapper;
import dev.yassiraitelghari.fms.repository.OrderRepository;
import dev.yassiraitelghari.fms.service.building.BuildingService;
import dev.yassiraitelghari.fms.service.user.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final BuildingService buildingService;
    private final SupplierInventoryService supplierInventoryService;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, BuildingService buildingService, SupplierService supplierService, SupplierInventoryService supplierInventoryService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.buildingService = buildingService;
        this.supplierInventoryService = supplierInventoryService;
    }


    public List<OrderDetailDTO> getAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::orderToOrderDetailDTO).collect(Collectors.toList());
    }

    public OrderDetailDTO findById(UUID id) {
        Order order = this.getById(id);
        return orderMapper.orderToOrderDetailDTO(order);
    }

    public Order getById(UUID id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderUUIDNotFoundException("Order UUID not found"));
    }

    public OrderDTO add(OrderCreateDTO order) {
        Building building = buildingService.getById(order.getBuildingId());
        SupplierInventory supplierInventory = supplierInventoryService.getById(order.getSupplierInventoryId());
        Order newOrder = orderMapper.orderCreateDTOToOrder(order);
        newOrder.setOrderStatus(OrderStatus.PENDING);
        newOrder.setBuilding(building);
        newOrder.setSupplierInventory(supplierInventory);
        return orderMapper.orderToOrderDTO(orderRepository.save(newOrder));

    }

    public OrderDetailDTO edit(UUID id, OrderUpdateDTO order) {
        Order updatedOrder = this.getById(id);
        Building building = buildingService.getById(order.getBuildingId());
        SupplierInventory supplierInventory = supplierInventoryService.getById(order.getSupplierInventoryId());
        updatedOrder.setOrderStatus(OrderStatus.valueOf(order.getStatus()));
        updatedOrder.setQuantity(order.getQuantity());
        updatedOrder.setBuilding(building);
        updatedOrder.setSupplierInventory(supplierInventory);
        OrderDetailDTO orderDetailDTO = orderMapper.orderToOrderDetailDTO(orderRepository.save(updatedOrder));
        orderDetailDTO.setStatus(OrderStatus.valueOf(order.getStatus()));
        return orderDetailDTO;
    }


    public Order edit(Order order) {
        return orderRepository.save(order);
    }

    public void delete(UUID id) {
        Order order = this.getById(id);
        orderRepository.deleteById(order.getId());
    }

}


