package com.example.cashflow.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class TransactionDTO {

    private String description;
    private BigDecimal value;

}
