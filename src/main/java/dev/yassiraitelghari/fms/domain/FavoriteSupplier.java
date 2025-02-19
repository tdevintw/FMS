package dev.yassiraitelghari.fms.domain;

import dev.yassiraitelghari.fms.domain.user.Manager;
import dev.yassiraitelghari.fms.domain.user.Supplier;
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
@Table(name = "favorite_suppliers")
public class FavoriteSupplier {
    @Id
    private UUID id ;
    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private Manager manager;
    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier ;
    private LocalDateTime creationDate ;
    private double ordersCount ;
}
