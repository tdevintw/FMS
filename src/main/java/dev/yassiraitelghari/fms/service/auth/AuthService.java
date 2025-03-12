package dev.yassiraitelghari.fms.service.auth;

import dev.yassiraitelghari.fms.domain.enums.Role;
import dev.yassiraitelghari.fms.domain.user.User;
import dev.yassiraitelghari.fms.dto.request.register.UserRegisterDTO;
import dev.yassiraitelghari.fms.dto.request.user.ResetPasswordDTO;
import dev.yassiraitelghari.fms.dto.response.TokenVM;
import dev.yassiraitelghari.fms.exception.*;
import dev.yassiraitelghari.fms.mapper.UserMapper;
import dev.yassiraitelghari.fms.repository.UserRepository;
import dev.yassiraitelghari.fms.service.user.ManagerService;
import dev.yassiraitelghari.fms.service.user.ShipperService;
import dev.yassiraitelghari.fms.service.user.SupplierService;
import dev.yassiraitelghari.fms.service.user.UserService;
import dev.yassiraitelghari.fms.service.email.EmailService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {


    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final ManagerService managerService;
    private final ShipperService shipperService;
    private final SupplierService supplierService;


    public AuthService(UserRepository userRepository, JwtService jwtService, UserMapper userMapper, UserService userService, PasswordEncoder passwordEncoder, EmailService emailService, ManagerService managerService, ShipperService shipperService, SupplierService supplierService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.managerService = managerService;
        this.shipperService = shipperService;
        this.supplierService = supplierService;
    }

    public TokenVM register(@Valid UserRegisterDTO user, String clientOrigin) {

        userService.findByUsername(user.getUsername())
                .ifPresent(existingUser -> {
                    throw new UsernameAlreadyExistsException("Username already exists");
                });

        userService.findByEmail(user.getEmail())
                .ifPresent(existingUser -> {
                    throw new UsernameAlreadyExistsException("Email already exists");
                });

        User newUser = userMapper.registredUserDTOToUser(user);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdateDate(LocalDateTime.now());
        newUser.setRole(Role.valueOf(user.getRole()));
        newUser.setVerificationToken(generateVerificationToken());

        registerUserWithRole(newUser, Role.valueOf(user.getRole()));

        String authToken = jwtService.generateToken(newUser.getUsername());
        String refreshToken = jwtService.generateRefreshToken(newUser.getUsername());
        emailService.sendVerificationEmail(newUser.getEmail(), newUser.getVerificationToken(), clientOrigin);
        return TokenVM.builder().token(authToken).refreshToken(refreshToken).build();
    }


    public TokenVM login(String username, String password) {

        User user = userRepository.findByUsername(username).orElseThrow(() -> new InvalidCredentialsException("Username or Password is incorrect !"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Username or Password is incorrect !");
        }


        String authToken = jwtService.generateToken(user.getUsername());
        String refreshToken = jwtService.generateRefreshToken(user.getUsername());
        return TokenVM.builder()
                .token(authToken)
                .refreshToken(refreshToken)
                .build();

    }


    public TokenVM refresh(String refreshToken) {

        if (jwtService.isTokenExpired(refreshToken)) {
            throw new ExpiredJwtException(null, null, "Refresh token has expired");
        }
        String username = jwtService.extractUsername(refreshToken);

        if (!jwtService.isTokenValid(refreshToken, username)) {
            throw new UsernameOrPasswordInvalidException("Invalid refresh token");
        }

        String newAccessToken = jwtService.generateToken(username);


        return new TokenVM(newAccessToken, refreshToken);
    }

    public void verifyEmail(String token) {
        User user = userRepository.findByVerificationToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid verification token."));

        user.setVerified(true);
        user.setVerificationToken(null);
        userRepository.save(user);

    }

    public void forgotPassword(String email, String clientOrigin) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

        String resetToken = generatePasswordResetToken();
        user.setPasswordResetToken(resetToken);
        user.setPasswordResetTokenExpiry(LocalDateTime.now().plusHours(1));
        userRepository.save(user);

        emailService.sendPasswordResetEmail(user.getEmail(), resetToken, clientOrigin);
    }

    public void resetPassword(String token, ResetPasswordDTO resetPasswordDTO) {
        User user = userRepository.findByPasswordResetToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid password reset token."));

        if (user.getPasswordResetTokenExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Password reset token has expired.");
        }
        String newPassword = resetPasswordDTO.getNewPassword();
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setPasswordResetToken(null);
        user.setPasswordResetTokenExpiry(null);
        userRepository.save(user);

    }

    public String generateVerificationToken() {
        String token = UUID.randomUUID().toString();

        List<User> user = userRepository.findAllByVerificationToken(token);

        if (!user.isEmpty()) return generateVerificationToken();

        return token;
    }

    public String generatePasswordResetToken() {
        String token = UUID.randomUUID().toString();

        List<User> user = userRepository.findAllByPasswordResetToken(token);

        if (!user.isEmpty()) return generatePasswordResetToken();

        return token;
    }

    private boolean isEmail(String input) {
        return input != null && input.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private void registerUserWithRole(User user, Role role) {
        switch (role) {
            case MANAGER:
                managerService.add(user);
                break;
            case SHIPPER:
                shipperService.add(user);
                break;
            case SUPPLIER:
                supplierService.add(user);
                break;
            default:
                throw new RoleNotFoundException("Role Not Found");
        }
    }
}