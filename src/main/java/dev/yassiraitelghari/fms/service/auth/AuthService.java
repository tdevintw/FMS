package dev.yassiraitelghari.fms.service.auth;

import dev.yassiraitelghari.fms.domain.user.User;
import dev.yassiraitelghari.fms.dto.request.register.UserDTO;
import dev.yassiraitelghari.fms.dto.response.TokenVM;
import dev.yassiraitelghari.fms.exception.UsernameOrPasswordInvalidException;
import dev.yassiraitelghari.fms.mapper.UserMapper;
import dev.yassiraitelghari.fms.repository.UserRepository;
import dev.yassiraitelghari.fms.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {


        private final UserRepository userRepository;
        private final JwtService jwtService;
        private final UserMapper userMapper;
        private final UserService userService;
        private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtService jwtService, UserMapper userMapper, UserService userService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public TokenVM register(@Valid UserDTO registerVM, String clientOrigin) {

            User newUser = userMapper.registredUserDTOToUser(registerVM);


            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            newUser.setCreationDate(LocalDateTime.now());
            newUser.setUpdateDate(LocalDateTime.now());

            User savedUser = userRepository.save(newUser);
            String authToken = jwtService.generateToken(savedUser.getUsername());
            String refreshToken = jwtService.generateRefreshToken(savedUser.getUsername());


            return TokenVM.builder().token(authToken).refreshToken(refreshToken).build();
        }


        public TokenVM login(String username, String password) {

            Optional<User> opUser;
            if (isEmail(username)) opUser = userRepository.findByEmailAndDeletedFalse(username);
            else opUser = userRepository.findByUsernameAndDeletedFalse(username);

            return opUser.filter(user -> passwordEncoder.matches(password, user.getPassword()))
                    .map(authenticatedUser -> {

                        String authToken = jwtService.generateToken(authenticatedUser.getUsername());
                        String refreshToken = jwtService.generateRefreshToken(authenticatedUser.getUsername());
                        return TokenVM.builder()
                                .token(authToken)
                                .refreshToken(refreshToken)
                                .build();
                    })
                    .orElseThrow(() -> new UsernameOrPasswordInvalidException("Invalid credentials."));
        }


        public TokenVM refresh(String refreshToken) {

            if(jwtService.isTokenExpired(refreshToken)) {
                throw new ExpiredJwtException(null, null, "Refresh token has expired");
            }
            String username = jwtService.extractUsername(refreshToken);

            if (!jwtService.isTokenValid(refreshToken,username )) {
                throw new UsernameOrPasswordInvalidException("Invalid refresh token");
            }

            String newAccessToken = jwtService.generateToken(username);


            return new TokenVM(newAccessToken, refreshToken);
        }



        public String generateVerificationToken() {
            String token = UUID.randomUUID().toString();

            List<User> user = userRepository.findAllByVerificationToken(token);

            if(!user.isEmpty()) return generateVerificationToken();

            return token;
        }

        public String generatePasswordResetToken() {
            String token = UUID.randomUUID().toString();

            List<User>  user = userRepository.findAllByPasswordResetToken(token);

            if(!user.isEmpty()) return generatePasswordResetToken();

            return token;
        }

        private boolean isEmail(String input) {
            return input != null && input.matches("^[A-Za-z0-9+_.-]+@(.+)$");
        }
}
