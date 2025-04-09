package mdg.miguel.mdgproject.dtos;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import mdg.miguel.mdgproject.enums.PaymentMethod;
import mdg.miguel.mdgproject.enums.PaymentType;

public record PaymentReceiptDTO(String description, PaymentType paymentType,
        PaymentMethod paymentMethod, LocalDate paymentDate,
        String paymentAmount, MultipartFile receiptFile) {

}
