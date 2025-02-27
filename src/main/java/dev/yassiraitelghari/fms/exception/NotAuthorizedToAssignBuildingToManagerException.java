package dev.yassiraitelghari.fms.exception;

public class NotAuthorizedToAssignBuildingToManagerException extends RuntimeException{
    public NotAuthorizedToAssignBuildingToManagerException(String message){
        super(message);
    }
}
