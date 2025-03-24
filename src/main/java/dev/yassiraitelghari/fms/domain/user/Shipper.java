package dev.yassiraitelghari.fms.domain.user;

import dev.yassiraitelghari.fms.domain.supply.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shippers")
public class Shipper extends User{
   @OneToMany(mappedBy = "shipper" )
   private List<Order> orders;
}
