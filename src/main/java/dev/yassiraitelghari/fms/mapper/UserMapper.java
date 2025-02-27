package dev.yassiraitelghari.fms.mapper;

import dev.yassiraitelghari.fms.domain.user.User;
import dev.yassiraitelghari.fms.dto.request.register.UserRegisterDTO;
import dev.yassiraitelghari.fms.dto.response.user.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User registredUserDTOToUser(UserRegisterDTO userRegisterDTO);
    UserDTO userToUserDTO(User user);
}
