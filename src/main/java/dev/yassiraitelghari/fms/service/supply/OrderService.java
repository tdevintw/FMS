package dev.yassiraitelghari.fms.service.supply;

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
        List<Order> cities = OrderRepository.findAll();
        return cities.stream().map(OrderMapper::OrderToOrderDetailDTO).collect(Collectors.toList());
    }

    public OrderDetailDTO findById(UUID id) {
        Order Order = this.getById(id);
        return OrderMapper.OrderToOrderDetailDTO(Order);
    }

    public Order getById(UUID id) {
        return OrderRepository.findById(id).orElseThrow(() -> new OrderUUIDNotFound("Order UUID not found"));
    }

    public OrderDTO add(OrderCreateDTO Order) {
        Order newOrder = new Order();
        newOrder.setOrder(Order.getOrder());
        Order savedOrder = OrderRepository.save(newOrder);
        return OrderMapper.OrderToOrderDTO(savedOrder);
    }

    public OrderDetailDTO edit(UUID id, OrderUpdateDTO Order) {
        Order updatedOrder = this.getById(id);
        updatedOrder.setOrder(Order.getOrder());
        OrderRepository.save(updatedOrder);
        return OrderMapper.OrderToOrderDetailDTO(updatedOrder);
    }


    public Order edit(Order Order) {
        return OrderRepository.save(Order);
    }

    public void delete(UUID id) {
        Order Order = this.getById(id);
        OrderRepository.deleteById(Order.getId());
    }

}


