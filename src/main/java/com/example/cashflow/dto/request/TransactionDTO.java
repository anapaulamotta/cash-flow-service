package com.example.cashflow.dto.request;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {

    private String description;
    private BigDecimal value;

}
