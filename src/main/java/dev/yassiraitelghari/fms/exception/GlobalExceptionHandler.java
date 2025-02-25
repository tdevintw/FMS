package dev.yassiraitelghari.fms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleFoodNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

    }
    @ExceptionHandler(UserNameAlreadyExistsException.class)
    public ResponseEntity<?> handleFoodNotFound(UserNameAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<?> handleFoodNotFound(RoleNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

    }





    @ExceptionHandler(FoodUUIDNotFound.class)
    public ResponseEntity<?> handleFoodNotFound(FoodUUIDNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

    }

    @ExceptionHandler(UsernameOrPasswordInvalidException.class)
    public ResponseEntity<?> handleUsernameOrPasswordInvalid(FoodUUIDNotFound ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

    }

    @ExceptionHandler(CategoryUUIDNotFound.class)
    public ResponseEntity<?> handleCategoryNotFound(CategoryUUIDNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

    }

    @ExceptionHandler(BuildingUUIDNotFound.class)
    public ResponseEntity<?> handleCategoryNotFound(BuildingUUIDNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e){
        Map<String , String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error->{
            String fieldName = ((FieldError) error).getField() ;
            String message = error.getDefaultMessage() ;
            errors.put(fieldName , message);
        });
        return ResponseEntity.badRequest().body(errors) ;
    }

}
