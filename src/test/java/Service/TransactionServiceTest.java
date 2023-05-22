package Service;

import com.example.cashflow.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    TransactionService service;

    @Test
    void whenInsertTransaction_thenSuccess(){

    }

}
