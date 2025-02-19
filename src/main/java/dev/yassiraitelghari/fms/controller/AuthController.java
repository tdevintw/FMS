package dev.yassiraitelghari.fms.controller;


import dev.yassiraitelghari.fms.dto.request.login.UserLoginDTO;
import dev.yassiraitelghari.fms.dto.request.register.UserDTO;
import dev.yassiraitelghari.fms.dto.response.TokenVM;
import dev.yassiraitelghari.fms.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authenticationService;

    public AuthController(AuthService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")

    public ResponseEntity<TokenVM> register(
            @RequestBody  UserDTO userDTO, HttpServletRequest request) {

        String clientOrigin = request.getHeader(HttpHeaders.ORIGIN);
        if (clientOrigin == null) {
            clientOrigin = request.getHeader(HttpHeaders.REFERER);
        }
        return ResponseEntity.ok(authenticationService.register(userDTO, clientOrigin));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenVM> login(
                                         @RequestBody @Valid UserLoginDTO request) {

        TokenVM response = authenticationService.login(request.getUsername(), request.getPassword());
        if (response.getToken() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(response);
    }





@PostMapping("/refresh")

    public ResponseEntity<TokenVM> refresh(
                                           @RequestBody String refreshToken) {
        return ResponseEntity.ok(authenticationService.refresh(refreshToken));
    }

}