package dev.yassiraitelghari.fms.dto.request.login;

import jakarta.validation.constraints.NotBlank;

public class UserLoginDTO {


    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    private String password;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

