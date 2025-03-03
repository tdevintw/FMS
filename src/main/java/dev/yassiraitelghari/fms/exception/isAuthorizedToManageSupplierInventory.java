package dev.yassiraitelghari.fms.exception;

public class isAuthorizedToManageSupplierInventory extends RuntimeException{
    public isAuthorizedToManageSupplierInventory(String message){
        super(message);
    }
}
