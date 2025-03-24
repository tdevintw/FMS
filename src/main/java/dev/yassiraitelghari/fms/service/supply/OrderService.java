package dev.yassiraitelghari.fms.service.supply;

import dev.yassiraitelghari.fms.domain.building.Building;
import dev.yassiraitelghari.fms.domain.enums.OrderStatus;
import dev.yassiraitelghari.fms.domain.supply.Order;
import dev.yassiraitelghari.fms.domain.supply.SupplierInventory;
import dev.yassiraitelghari.fms.domain.user.Manager;
import dev.yassiraitelghari.fms.domain.user.Shipper;
import dev.yassiraitelghari.fms.domain.user.Supplier;
import dev.yassiraitelghari.fms.dto.request.order.OrderCreateDTO;
import dev.yassiraitelghari.fms.dto.request.order.OrderUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.order.OrderDTO;
import dev.yassiraitelghari.fms.dto.response.order.OrderDetailDTO;
import dev.yassiraitelghari.fms.exception.OrderUUIDNotFoundException;
import dev.yassiraitelghari.fms.mapper.OrderMapper;
import dev.yassiraitelghari.fms.repository.OrderRepository;
import dev.yassiraitelghari.fms.service.building.BuildingService;
import dev.yassiraitelghari.fms.service.user.ManagerService;
import dev.yassiraitelghari.fms.service.user.ShipperService;
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
    private final ManagerService managerService;
    private final SupplierService supplierService;
    private final ShipperService shipperService;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, BuildingService buildingService, SupplierInventoryService supplierInventoryService, ManagerService managerService, SupplierService supplierService, ShipperService shipperService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.buildingService = buildingService;
        this.supplierInventoryService = supplierInventoryService;
        this.managerService = managerService;
        this.supplierService = supplierService;
        this.shipperService = shipperService;
    }


    public List<OrderDetailDTO> getAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::orderToOrderDetailDTO).collect(Collectors.toList());
    }

    public List<OrderDetailDTO> getAllOfAManager(UUID id) {
        Manager manager = managerService.findById(id);
        List<Building> buildings = manager.getBuildings();
        List<Order> orders = buildings.stream()
                .flatMap(building -> building.getOrders().stream())
                .collect(Collectors.toUnmodifiableList());
        return orders.stream().map(orderMapper::orderToOrderDetailDTO).collect(Collectors.toList());
    }

    public List<OrderDetailDTO> getAllOfAShipper(UUID id) {
        Shipper shipper = shipperService.getById(id);
        List<Order> orders = shipper.getOrders();
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
        updatedOrder.setOrderStatus(OrderStatus.valueOf(order.getOrderStatus()));
        updatedOrder.setQuantity(order.getQuantity());
        updatedOrder.setBuilding(building);
        updatedOrder.setSupplierInventory(supplierInventory);
        OrderDetailDTO orderDetailDTO = orderMapper.orderToOrderDetailDTO(orderRepository.save(updatedOrder));
        orderDetailDTO.setOrderStatus(OrderStatus.valueOf(order.getOrderStatus()));
        return orderDetailDTO;
    }

    public OrderDetailDTO setStatusToDelivered(UUID id) {
        Order updatedOrder = this.getById(id);
        updatedOrder.setOrderStatus(OrderStatus.DELIVERED);
        updatedOrder.setCurrentLocation("Delivered");
        OrderDetailDTO orderDetailDTO = orderMapper.orderToOrderDetailDTO(orderRepository.save(updatedOrder));
        orderDetailDTO.setOrderStatus(OrderStatus.DELIVERED);
        orderDetailDTO.setCurrentLocation("Delivered");
        return orderDetailDTO;
    }


    public OrderDetailDTO assignShipper(UUID id, UUID shipperId) {
        Order order = this.getById(id);
        Shipper shipper = shipperService.getById(shipperId);
        order.setShipper(shipper);
        order.setOrderStatus(OrderStatus.IN_DELIVERY);
        orderRepository.save(order);
        return orderMapper.orderToOrderDetailDTO(order);
    }

    public OrderDetailDTO setCurrentLocation(UUID id, String currentLocation) {
        Order order = this.getById(id);
        order.setCurrentLocation(currentLocation);
        orderRepository.save(order);
        return orderMapper.orderToOrderDetailDTO(order);
    }

    public Order edit(Order order) {
        return orderRepository.save(order);
    }

    public void delete(UUID id) {
        Order order = this.getById(id);
        orderRepository.deleteById(order.getId());
    }

    public List<OrderDetailDTO> getOrdersOfASupplier(UUID id) {
        Supplier supplier = supplierService.getById(id);
        List<Order> orders = supplier.getInventories().stream().flatMap(inventory -> inventory.getOrders().stream()).collect(Collectors.toUnmodifiableList());
        return orders.stream().map(orderMapper::orderToOrderDetailDTO).collect(Collectors.toList());
    }

}


