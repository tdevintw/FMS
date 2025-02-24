package dev.yassiraitelghari.fms.mapper;

import dev.yassiraitelghari.fms.domain.building.Building;
import dev.yassiraitelghari.fms.domain.supply.Order;
import dev.yassiraitelghari.fms.dto.request.building.BuildingCreateDTO;
import dev.yassiraitelghari.fms.dto.request.building.BuildingUpdateDTO;
import dev.yassiraitelghari.fms.dto.request.order.OrderCreateDTO;
import dev.yassiraitelghari.fms.dto.request.order.OrderUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.building.BuildingDTO;
import dev.yassiraitelghari.fms.dto.response.building.BuildingDetailDTO;
import dev.yassiraitelghari.fms.dto.response.order.OrderDTO;
import dev.yassiraitelghari.fms.dto.response.order.OrderDetailDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order orderCreateDTOToOrder(OrderCreateDTO order);
    OrderDTO orderToOrderDetailDTO(Order order);
    Order orderUpdateDTOToOrder(OrderUpdateDTO order);
}
