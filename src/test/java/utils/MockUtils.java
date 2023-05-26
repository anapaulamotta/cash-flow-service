package utils;

import com.example.cashflow.domain.Transaction;
import com.example.cashflow.dto.request.TransactionDTO;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@UtilityClass
public class MockUtils {

    public static List<Transaction> transactionList(){

        List<Transaction> transactions = new ArrayList<>();

        Transaction transaction1 = new Transaction(1l,"credit","manutenção", BigDecimal.valueOf(23.5), LocalDateTime.now());
        Transaction transaction2 = new Transaction(2l,"credit","manutenção", BigDecimal.valueOf(23.5), LocalDateTime.now());

        transactions.add(transaction1);
        transactions.add(transaction2);

        return transactions;
    }

    public static List<TransactionDTO> transactionDtoList(){

        List<TransactionDTO> transactions = new ArrayList<>();

        TransactionDTO transaction1 = new TransactionDTO("manutenção", BigDecimal.valueOf(23.5));
        TransactionDTO transaction2 = new TransactionDTO("manutenção", BigDecimal.valueOf(23.5));

        transactions.add(transaction1);
        transactions.add(transaction2);

        return transactions;
    }

}
