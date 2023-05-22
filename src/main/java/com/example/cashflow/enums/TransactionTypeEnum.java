package com.example.cashflow.enums;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionTypeEnum {

    CREDIT("credit"),
    DEBIT("debit");

    private String name;

}
