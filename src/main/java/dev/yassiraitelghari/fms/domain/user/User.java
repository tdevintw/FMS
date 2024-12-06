package dev.yassiraitelghari.fms.domain.user;


import java.time.LocalDateTime;
import java.util.UUID;

public abstract class User {
    protected UUID id ;
    protected String email ;
    protected String username ;
    protected String password ;
    protected LocalDateTime joinedAt ;
    protected LocalDateTime updatedAt ;
}
