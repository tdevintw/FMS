package dev.yassiraitelghari.fms.service.supply;

import dev.yassiraitelghari.fms.domain.supply.Order;
import dev.yassiraitelghari.fms.dto.request.order.OrderCreateDTO;
import dev.yassiraitelghari.fms.dto.request.order.OrderUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.order.OrderDTO;
import dev.yassiraitelghari.fms.dto.response.order.OrderDetailDTO;
import dev.yassiraitelghari.fms.exception.OrderUUIDNotFoundException;
import dev.yassiraitelghari.fms.mapper.OrderMapper;
import dev.yassiraitelghari.fms.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }



    public List<OrderDetailDTO> getAll(){
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
        Order newOrder = orderMapper.orderCreateDTOToOrder(order);
        return orderMapper.orderToOrderDTO(orderRepository.save(newOrder));

    }

    public OrderDetailDTO edit(UUID id, OrderUpdateDTO order) {
        Order updatedOrder = this.getById(id);
        updatedOrder.setOrderStatus(order.getStatus());
        updatedOrder.setQuantity(order.getQuantity());
        return orderMapper.orderToOrderDetailDTO(orderRepository.save(updatedOrder));

    }


    public Order edit(Order order) {
        return orderRepository.save(order);
    }

    public void delete(UUID id) {
        Order order = this.getById(id);
        orderRepository.deleteById(order.getId());
    }

}


