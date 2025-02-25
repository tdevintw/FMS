package dev.yassiraitelghari.fms.service.auth;


import dev.yassiraitelghari.fms.domain.enums.Role;
import dev.yassiraitelghari.fms.domain.user.User;
import dev.yassiraitelghari.fms.exception.RoleNotFoundException;
import dev.yassiraitelghari.fms.exception.UserNotFoundException;
import dev.yassiraitelghari.fms.service.user.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService  {

    private final String SECRET="YfB5Djsl4mHYu7l9f8TFSrDhRUv3B3MwPfM02y5T0pE=" ;
    private final UserService userService;

    public JwtService(UserService userService) {
        this.userService = userService;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(String username) {
        return generateToken(new HashMap<>(), username);
    }

    public String generateToken(Map<String, Object> claims, String username) {
        claims.put("role", extractRole(username));
        // put permissions
        claims.put("permissions", extractRole(username).getAuthorities());
        claims.put("username", username);
        userService.findByUsername(username).ifPresent(user -> claims.put("id", user.getId()));

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24   )) // 1 minute
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String username) {
        return generateRefreshToken(new HashMap<>(), username);
    }

    public String generateRefreshToken(Map<String, Object> claims, String username) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // 1 week
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }


    private Claims getClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Role extractRole(String username)  {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Role role = user.getRole();
        if (role == null) {
            throw new RoleNotFoundException("Role not found");
        }
        return role;
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

