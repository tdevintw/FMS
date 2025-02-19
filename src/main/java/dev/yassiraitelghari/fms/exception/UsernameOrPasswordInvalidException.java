package dev.yassiraitelghari.fms.exception;

public class UsernameOrPasswordInvalidException extends RuntimeException{
    public  UsernameOrPasswordInvalidException(String m){
        super(m);
    }
}
