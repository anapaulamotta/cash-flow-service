package com.example.cashflow.service;

import com.example.cashflow.domain.Transaction;
import com.example.cashflow.dto.request.TransactionDTO;
import com.example.cashflow.enums.TransactionTypeEnum;
import com.example.cashflow.exception.WrongTypeException;
import com.example.cashflow.mapper.TransactionMapper;
import com.example.cashflow.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionService {

    @Autowired
    TransactionMapper mapper;

    @Autowired
    TransactionRepository repository;

    @Autowired
    ReportService reportService;

    public List<Transaction> insertTransaction(List<TransactionDTO> transactionDTOS, String type) throws WrongTypeException {

        List<Transaction> transactions = mapper.toEntities(transactionDTOS);

        if (type.equals(TransactionTypeEnum.CREDIT.getName()) ||
                type.equals(TransactionTypeEnum.DEBIT.getName())){

            for (Transaction transaction:transactions){
                transaction.setType(type);
                transaction.setDateTime(LocalDateTime.now());
            }

        } else{
            throw new WrongTypeException();
        }

        repository.saveAll(transactions);

        return transactions;
    }

    public void findData() throws IOException {

        LocalDate today = LocalDate.now();

        List<Transaction> credits = repository.findAllByDateTimeBetweenAndType(LocalDateTime.of(today, LocalTime.MIN),
                LocalDateTime.of(today, LocalTime.MAX), TransactionTypeEnum.CREDIT.getName());

        List<Transaction> debits = repository.findAllByDateTimeBetweenAndType(LocalDateTime.of(today, LocalTime.MIN),
                LocalDateTime.of(today, LocalTime.MAX), TransactionTypeEnum.DEBIT.getName());

        getReport(credits,debits);

    }

    public void findData(LocalDate date) throws IOException {

        List<Transaction> credits = repository.findAllByDateTimeBetweenAndType(LocalDateTime.of(date, LocalTime.MIN),
                LocalDateTime.of(date, LocalTime.MAX), TransactionTypeEnum.CREDIT.getName());

        List<Transaction> debits = repository.findAllByDateTimeBetweenAndType(LocalDateTime.of(date, LocalTime.MIN),
                LocalDateTime.of(date, LocalTime.MAX), TransactionTypeEnum.DEBIT.getName());

        getReport(credits,debits);

    }

    public void findData(LocalDate initialDate, LocalDate finalDate) throws IOException {

        List<Transaction> credits = repository.findAllByDateTimeBetweenAndType(LocalDateTime.of(initialDate, LocalTime.MIN),
                LocalDateTime.of(finalDate, LocalTime.MAX), TransactionTypeEnum.CREDIT.getName());

        List<Transaction> debits = repository.findAllByDateTimeBetweenAndType(LocalDateTime.of(initialDate, LocalTime.MIN),
                LocalDateTime.of(finalDate, LocalTime.MAX), TransactionTypeEnum.DEBIT.getName());

        getReport(credits,debits);

    }

    public void getReport(List<Transaction> credits, List<Transaction> debits) throws IOException{

        List<String[]> stringCredits = new ArrayList<>();
        for (Transaction transaction : credits) {
            String[] array = new String[4];
            array[0] = transaction.getId().toString();
            array[1] = transaction.getDescription();
            array[2] = transaction.getValue().toString();
            array[3] = transaction.getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            stringCredits.add(array);
        }

        List<String[]> stringDebits = new ArrayList<>();
        for (Transaction transaction : debits) {
            String[] array = new String[4];
            array[0] = transaction.getId().toString();
            array[1] = transaction.getDescription();
            array[2] = transaction.getValue().toString();
            array[3] = transaction.getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            stringCredits.add(array);
        }

        BigDecimal credit = BigDecimal.valueOf(0);

        for (Transaction plus:credits){
            credit.add(plus.getValue());
        }

        BigDecimal debit = BigDecimal.valueOf(0);

        for (Transaction minus:debits){
            debit.add(minus.getValue());
        }

        String[] total = {"Total",(credit.subtract(debit)).toString()};

        reportService.getReport(stringCredits,stringDebits,total);
    }
}
