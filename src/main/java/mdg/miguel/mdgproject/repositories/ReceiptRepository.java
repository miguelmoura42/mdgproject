package mdg.miguel.mdgproject.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import mdg.miguel.mdgproject.entities.PaymentReceipt;
import mdg.miguel.mdgproject.enums.PaymentMethod;
import mdg.miguel.mdgproject.enums.PaymentType;

public interface ReceiptRepository extends JpaRepository<PaymentReceipt, Long> {
        List<PaymentReceipt> findByPaymentTypeAndPaymentDateBetween(
                        PaymentType paymentType, LocalDate startDate, LocalDate endDate);

        List<PaymentReceipt> findByPaymentMethodAndPaymentDateBetween(
                        PaymentMethod paymentMethod, LocalDate startDate, LocalDate endDate);

        Optional<PaymentReceipt> findByUuid(UUID uuid);

}