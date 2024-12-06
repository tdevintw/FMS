package dev.yassiraitelghari.fms.domain.supply;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private UUID id ;
    private double totalPrice ;
    private LocalDateTime createdAt ;
    private LocalDateTime updateAt ;
    private Order order;
}
