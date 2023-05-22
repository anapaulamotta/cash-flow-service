package com.example.cashflow.mapper;

import com.example.cashflow.domain.Transaction;
import com.example.cashflow.dto.request.TransactionDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionMapper {

    public List<Transaction> toEntities(List<TransactionDTO> dtos){

        List<Transaction> transactions = new ArrayList<>();

        for (TransactionDTO dto:dtos){
            Transaction transaction = new Transaction();

            transaction.setDescription(dto.getDescription());
            transaction.setValue(dto.getValue());

            transactions.add(transaction);
        }

        return transactions;

    }

}
