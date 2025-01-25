package pl.pjatk.s32006Bank;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BankServiceIntegration {
    @MockitoBean
    private OperationService operationService;
    @MockitoBean
    private ClientStorage clientStorage;
    @Autowired
    private BankService bankService;

    @Test
    public void testBankService() {
        Operation operation = new Operation();
        operation.setStatus(Status.Accepted);
        operation.setAmount(500);
        when(clientStorage.getClient(anyInt())).thenReturn(new Client(0, 1000));
        when(operationService.getOperation(any(), anyInt())).thenReturn(operation);
        bankService.pay(0, 500);
        assertThat(operation.getStatus()).isEqualTo(Status.Accepted);
    }
    @Test
    public void testBankService2() {
        Operation operation = new Operation();
        operation.setStatus(Status.Declined);
        operation.setAmount(1000);
        when(clientStorage.getClient(anyInt())).thenReturn(new Client(1, 2000));
        when(operationService.getOperation(any(), anyInt())).thenReturn(operation);
        bankService.recieve(0, 500);
        assertThat(operation.getStatus()).isEqualTo(Status.Declined);
    }
}
