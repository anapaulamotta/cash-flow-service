package com.example.cashflow.mapper;

import com.example.cashflow.domain.Transaction;
import com.example.cashflow.dto.request.TransactionDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TransactionMapper {

    public List<Transaction> toEntities(List<TransactionDTO> dtos);

}
