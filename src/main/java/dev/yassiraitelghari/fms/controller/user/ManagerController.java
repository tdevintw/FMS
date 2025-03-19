package dev.yassiraitelghari.fms.controller.user;

import dev.yassiraitelghari.fms.dto.request.user.UserUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.user.ManagerDTO;
import dev.yassiraitelghari.fms.dto.response.user.UserDTO;
import dev.yassiraitelghari.fms.service.user.ManagerService;
import dev.yassiraitelghari.fms.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {

    private final ManagerService managerService ;
private final UserService userService;


    public ManagerController(ManagerService managerService, UserService userService) {
        this.managerService = managerService;
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        UserDTO user = userService.findByUserDTOById(id);
        return ResponseEntity.status(200).body(user);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(200).body(managerService.getAll());
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UserUpdateDTO user, @PathVariable UUID id) {
        return ResponseEntity.status(200).body(managerService.update(id , user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        managerService.delete(id);
        return ResponseEntity.status(200).body("Supplier was deleted");
    }
}
