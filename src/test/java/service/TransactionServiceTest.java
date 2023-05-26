package service;

import com.example.cashflow.enums.TransactionTypeEnum;
import com.example.cashflow.exception.WrongTypeException;
import com.example.cashflow.mapper.TransactionMapper;
import com.example.cashflow.repository.TransactionRepository;
import com.example.cashflow.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import utils.MockUtils;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    TransactionService service;

    @Mock
    TransactionMapper mapper;

    @Mock
    TransactionRepository repository;

    @Test
    void whenInsertTransaction_thenSuccess() throws WrongTypeException {

        when(mapper.toEntities(any())).thenReturn(MockUtils.transactionList());

        service.insertTransaction(MockUtils.transactionDtoList(), TransactionTypeEnum.DEBIT.getName());

        verify(mapper, times(1)).toEntities(any());
        verify(repository, times(1)).saveAll(any());
    }

    @Test
    void whenInsertTransaction_thenThrowException() throws WrongTypeException{

        assertThrows(WrongTypeException.class, () -> {
            service.insertTransaction(MockUtils.transactionDtoList(), "Crébito");
        });

    }
}
