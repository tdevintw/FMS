package dev.yassiraitelghari.fms.domain.supply;

import jakarta.persistence.*;
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
@Entity
@Table(name = "payments")

public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;
    private double totalPrice ;
    private LocalDateTime creationDate ;
    private LocalDateTime updateDate ;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
