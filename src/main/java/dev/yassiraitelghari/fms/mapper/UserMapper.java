package dev.yassiraitelghari.fms.mapper;

import dev.yassiraitelghari.fms.domain.user.*;
import dev.yassiraitelghari.fms.dto.request.register.UserRegisterDTO;
import dev.yassiraitelghari.fms.dto.response.user.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User registredUserDTOToUser(UserRegisterDTO userRegisterDTO);

    UserDTO userToUserDTO(User user);

    SupplierDTO supplierToSupplierDTO(Supplier supplier);

    ManagerDTO managerToManagerDTO(Manager manager);

    ShipperDTO shipperToShipperDTO(Shipper shipper);

    AdminDTO adminToAdminDTO(Admin admin);

    UserDTO managerDTOTOUserDTO(ManagerDTO manager);
}
