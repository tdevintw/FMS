package dev.yassiraitelghari.fms.service.user;

import dev.yassiraitelghari.fms.domain.user.User;
import dev.yassiraitelghari.fms.exception.UserUUIDNotFound;
import dev.yassiraitelghari.fms.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service("userService")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    public User findByID(UUID id){
        return userRepository.findById(id).orElseThrow(()->new UserUUIDNotFound("User UUID Not Found"));
    }

    public Page<User> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<User> userPage = userRepository.findAll(pageable);

        return userPage;
    }

    public Page<User> searchByUsername(String username, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return userRepository.findByUsernameContainingOrEmailContaining(username, username, pageable);
    }

    public Optional<User> findByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        return userRepository.findByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );
    }

}
