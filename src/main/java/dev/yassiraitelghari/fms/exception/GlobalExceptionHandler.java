package dev.yassiraitelghari.fms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}
