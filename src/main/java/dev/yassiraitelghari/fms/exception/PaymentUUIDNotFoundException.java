package dev.yassiraitelghari.fms.exception;

public class PaymentUUIDNotFoundException extends RuntimeException{
    public PaymentUUIDNotFoundException(String message){
        super(message);
    }
}
