package dev.yassiraitelghari.fms.service.supply;

import dev.yassiraitelghari.fms.domain.supply.Payment;
import dev.yassiraitelghari.fms.dto.request.payment.PaymentCreateDTO;
import dev.yassiraitelghari.fms.dto.request.payment.PaymentUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.payment.PaymentDTO;
import dev.yassiraitelghari.fms.dto.response.payment.PaymentDetailDTO;
import dev.yassiraitelghari.fms.exception.PaymentUUIDNotFoundException;
import dev.yassiraitelghari.fms.mapper.PaymentMapper;
import dev.yassiraitelghari.fms.mapper.PaymentMapper;
import dev.yassiraitelghari.fms.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;


    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }


    public List<PaymentDetailDTO> getAll() {
        List<Payment> Payments = paymentRepository.findAll();
        return Payments.stream().map(paymentMapper::paymentToPaymentDetailDTO).collect(Collectors.toList());
    }

    public PaymentDetailDTO findById(UUID id) {
        Payment payment = this.getById(id);
        return paymentMapper.paymentToPaymentDetailDTO(payment);
    }

    public Payment getById(UUID id) {
        return paymentRepository.findById(id).orElseThrow(() -> new PaymentUUIDNotFoundException("Payment UUID not found"));
    }

    public PaymentDTO add(PaymentCreateDTO Payment) {
        Payment newPayment = paymentMapper.paymentCreateDTOToPayment(Payment);
        return paymentMapper.paymentToPaymentDTO(paymentRepository.save(newPayment));

    }

    public PaymentDetailDTO edit(UUID id, PaymentUpdateDTO payment) {
        Payment updatedPayment = this.getById(id);
        updatedPayment.setTotalPrice(payment.getTotalPrice());
        return paymentMapper.paymentToPaymentDetailDTO(paymentRepository.save(updatedPayment));
    }


    public Payment edit(Payment Payment) {
        return paymentRepository.save(Payment);
    }

    public void delete(UUID id) {
        Payment payment = this.getById(id);
        paymentRepository.deleteById(payment.getId());
    }

}
