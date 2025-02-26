package dev.yassiraitelghari.fms.exception;

public class BuildingAccessDeniedException extends RuntimeException {
    public BuildingAccessDeniedException(String message){
        super(message);
    }
}
