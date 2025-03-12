package dev.yassiraitelghari.fms.controller;


import dev.yassiraitelghari.fms.dto.request.login.UserLoginDTO;
import dev.yassiraitelghari.fms.dto.request.register.UserRegisterDTO;
import dev.yassiraitelghari.fms.dto.request.user.ResetPasswordDTO;
import dev.yassiraitelghari.fms.dto.response.TokenVM;
import dev.yassiraitelghari.fms.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<TokenVM> register(@RequestPart("user") @Valid UserRegisterDTO user, @RequestPart("image") MultipartFile file, HttpServletRequest request) {

        String clientOrigin = request.getHeader(HttpHeaders.ORIGIN);
        if (clientOrigin == null) {
            clientOrigin = request.getHeader(HttpHeaders.REFERER);
        }
        return ResponseEntity.ok(authService.register(user,file,clientOrigin));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenVM> login(@RequestBody @Valid UserLoginDTO user) {

        TokenVM response = authService.login(user.getUsername(), user.getPassword());
        if (response.getToken() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        authService.verifyEmail(token);
        return ResponseEntity.ok(" Email verified successfully");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email, HttpServletRequest request) {
        String clientOrigin = request.getHeader(HttpHeaders.ORIGIN);
        if (clientOrigin == null) {
            clientOrigin = request.getHeader(HttpHeaders.REFERER);
        }
        authService.forgotPassword(email, clientOrigin);
        return ResponseEntity.ok("Password reset email sent.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestBody @Valid ResetPasswordDTO resetPasswordDTO) {
        authService.resetPassword(token, resetPasswordDTO);
        return ResponseEntity.ok("Password reset successfully.");
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenVM> refresh(@RequestBody String refreshToken) {
        return ResponseEntity.ok(authService.refresh(refreshToken));
    }

}