package dev.yassiraitelghari.fms.exception;

public class OrderUUIDNotFoundException extends RuntimeException{
    public OrderUUIDNotFoundException(String message){
        super(message);
    }
}
