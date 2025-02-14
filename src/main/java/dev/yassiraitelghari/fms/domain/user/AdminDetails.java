package dev.yassiraitelghari.fms.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class AdminDetails extends User{

}
