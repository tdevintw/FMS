package dev.yassiraitelghari.fms.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super( message);
    }
}
