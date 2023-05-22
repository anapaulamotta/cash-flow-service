package com.example.cashflow.dto.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TransactionDTO {

    private String description;
    private BigDecimal value;

}
