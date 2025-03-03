package dev.yassiraitelghari.fms.domain.supply;

import dev.yassiraitelghari.fms.domain.user.Shipper;
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
@Table(name = "shipments")
public class
Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;
    private String currentLocation ;
    private LocalDateTime creationDate ;
    private LocalDateTime updateDate ;
    @ManyToOne
    @JoinColumn(name = "shipper_id")
    private Shipper shipper;
    @OneToOne(mappedBy = "shipment", cascade = CascadeType.PERSIST)
    private Order order ;
}
