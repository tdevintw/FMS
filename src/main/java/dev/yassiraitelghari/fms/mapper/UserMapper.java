package dev.yassiraitelghari.fms.mapper;

import dev.yassiraitelghari.fms.domain.user.User;
import dev.yassiraitelghari.fms.dto.request.register.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User registredUserDTOToUser(UserDTO userDTO);
}
