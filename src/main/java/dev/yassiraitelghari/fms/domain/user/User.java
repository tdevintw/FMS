package dev.yassiraitelghari.fms.domain.user;


import dev.yassiraitelghari.fms.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Table(name = "users")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id ;
    protected String email ;
    protected String username ;
    protected String password ;
    private String imageUrl;
    protected String verificationToken;
    protected String passwordResetToken;
    protected LocalDateTime passwordResetTokenExpiry;



    protected LocalDateTime creationDate ;
    protected LocalDateTime updateDate ;
    protected boolean isVerified ;
    protected Role role ;


    protected LocalDateTime createdAt;



    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

//
