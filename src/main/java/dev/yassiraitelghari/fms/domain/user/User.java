package dev.yassiraitelghari.fms.domain.user;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Table(name = "users")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class User {
    @Id
    protected UUID id ;
    protected String email ;
    protected String username ;
    protected String password ;
    private LocalDateTime creationDate ;
    private LocalDateTime updateDate ;
}
