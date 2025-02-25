package dev.yassiraitelghari.fms.controller.supply;


import dev.yassiraitelghari.fms.dto.request.payment.PaymentCreateDTO;
import dev.yassiraitelghari.fms.dto.request.payment.PaymentUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.payment.PaymentDTO;
import dev.yassiraitelghari.fms.dto.response.payment.PaymentDetailDTO;
import dev.yassiraitelghari.fms.service.supply.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final  PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        PaymentDTO Payment = paymentService.findById(id);
        return ResponseEntity.status(200).body(Payment);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<PaymentDetailDTO> payments = paymentService.getAll();
        return ResponseEntity.status(200).body(payments);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','SUPPLIER')")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody  PaymentCreateDTO payment) {
        return ResponseEntity.status(201).body(paymentService.add(payment));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','SUPPLIER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody PaymentUpdateDTO payment, @PathVariable UUID id) {
        return ResponseEntity.status(201).body(paymentService.edit(id , payment));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','SUPPLIER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        paymentService.delete(id);
        return ResponseEntity.status(200).body("Payment Was deleted");
    }

}
