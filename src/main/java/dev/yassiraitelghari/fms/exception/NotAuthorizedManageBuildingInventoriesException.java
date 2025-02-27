package dev.yassiraitelghari.fms.exception;

public class NotAuthorizedManageBuildingInventoriesException extends RuntimeException{
    public NotAuthorizedManageBuildingInventoriesException(String message){
        super(message);
    }
}
