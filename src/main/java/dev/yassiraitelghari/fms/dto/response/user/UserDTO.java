package dev.yassiraitelghari.fms.dto.response.user;

import dev.yassiraitelghari.fms.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    protected UUID id;
    protected String username;
    protected String email;
    protected Role role;
}