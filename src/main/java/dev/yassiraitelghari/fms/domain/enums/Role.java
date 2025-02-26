package dev.yassiraitelghari.fms.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import dev.yassiraitelghari.fms.domain.enums.Permission.*;


public enum Role {

    ADMIN(
            Set.of(
            )
    ) ,
    MANAGER (
            Set.of(
            )
    ),
    SUPPLIER(
            Set.of(
            )
    ) ,
    SHIPPER(
            Set.of(
            )
    );

    @Getter
    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities =getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + name()));
        return authorities;
    }
}