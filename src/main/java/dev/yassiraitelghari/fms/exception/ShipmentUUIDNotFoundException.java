package dev.yassiraitelghari.fms.exception;

public class ShipmentUUIDNotFoundException extends RuntimeException{
    public ShipmentUUIDNotFoundException(String message){
        super(message);
    }
}
