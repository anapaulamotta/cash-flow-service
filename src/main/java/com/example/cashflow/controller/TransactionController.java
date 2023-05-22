package com.example.cashflow.controller;

import com.example.cashflow.domain.Transaction;
import com.example.cashflow.dto.request.TransactionDTO;
import com.example.cashflow.exception.WrongTypeException;
import com.example.cashflow.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {


    @Autowired
    TransactionService service;

    @GetMapping("/report")
    public void getReport(@RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate initialDate,
                          @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate finalDate) throws IOException {

        /* Se informado as duas datas, o relatório será do intervalo
           Se for apenas a primeira, o relatório será do dia informado
           Caso não tenham datas, o relatório será do dia atual. */

        if(nonNull(initialDate) && nonNull(finalDate) && initialDate.isBefore(finalDate)){
            service.findData(initialDate,finalDate);
        } else if (nonNull(initialDate) && isNull(finalDate)) {
            service.findData(initialDate);
        } else if (isNull(initialDate) && isNull(finalDate)) {
            service.findData();
        }else {

        }

    }

    @PostMapping("/{type}")
    public List<Transaction> addTransaction(@RequestBody List<TransactionDTO> transactionDTOList,
                                 @PathVariable String type) throws WrongTypeException {

        List<Transaction> transactions = service.insertTransaction(transactionDTOList, type.toLowerCase());

        return transactions;
    }

}
