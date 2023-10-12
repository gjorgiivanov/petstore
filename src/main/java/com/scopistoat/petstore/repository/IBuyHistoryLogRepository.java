package com.scopistoat.petstore.repository;

import com.scopistoat.petstore.model.BuyHistoryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface IBuyHistoryLogRepository extends JpaRepository<BuyHistoryLog, LocalDate> {
}
