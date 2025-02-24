package dev.yassiraitelghari.fms.dto.request.login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {


    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotBlank(message = "Password is required.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    @Pattern(regexp = ".*[a-z].*", message = "Password must contain at least one lowercase letter.")
    @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase letter.")
    @Pattern(regexp = ".*[0-9].*", message = "Password must contain at least one digit.")    private String password;

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

