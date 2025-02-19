package dev.yassiraitelghari.fms.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TokenVM {
    private String token;
    private String refreshToken;
}