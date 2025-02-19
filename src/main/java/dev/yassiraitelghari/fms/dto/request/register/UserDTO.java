package dev.yassiraitelghari.fms.dto.request.register;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private  String email ;
    private  String username ;
    private  String password ;
    private  String Role;
}
