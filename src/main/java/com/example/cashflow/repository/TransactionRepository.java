package com.example.cashflow.repository;

import com.example.cashflow.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByDateTimeBetweenAndType(LocalDateTime initialDate,
                                                      LocalDateTime finalDate,
                                                      String type);


}
