package dev.yassiraitelghari.fms.mapper;

import dev.yassiraitelghari.fms.domain.supply.Payment;
import dev.yassiraitelghari.fms.dto.request.payment.PaymentCreateDTO;
import dev.yassiraitelghari.fms.dto.request.payment.PaymentUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.payment.PaymentDTO;
import dev.yassiraitelghari.fms.dto.response.payment.PaymentDetailDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    Payment paymentCreateDTOToPayment(PaymentCreateDTO payment);
    PaymentDTO paymentToPaymentDTO(Payment payment);
    PaymentDetailDTO paymentToPaymentDetailDTO(Payment payment);
    Payment paymentUpdateDTOToPayment(PaymentUpdateDTO payment);
}
