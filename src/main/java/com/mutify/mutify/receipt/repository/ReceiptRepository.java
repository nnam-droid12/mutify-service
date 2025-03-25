package com.mutify.mutify.receipt.repository;


import com.mutify.mutify.receipt.entities.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDateTime;
import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    List<Receipt> findByBusinessId(Long businessId);
    List<Receipt> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);
}


