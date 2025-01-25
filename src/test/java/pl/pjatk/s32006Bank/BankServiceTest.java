package pl.pjatk.s32006Bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BankServiceTest {
    private Client client;
    private Operation operation;
    @Mock
    private ClientStorage clientStorage;
    @Mock
    private OperationService operationService;
    @InjectMocks
    private BankService bankService;
    @Captor
    private ArgumentCaptor<Operation> operationCaptor;
    @Captor
    private ArgumentCaptor<Client> clientCaptor;

    @Test
    void registerClient() {
        doNothing().when(clientStorage).addClient(clientCaptor.capture());
        bankService.registerClient(2000);
        client = clientCaptor.getValue();
        assertThat(client.getBalance()).isEqualTo(2000);
    }
    @Test
    void operationServiceTest() {
        operationService.getOperation(Status.Error);
        verify(operationService).getOperation(Status.Error);
    }
    @Test
    void clientStorageTest() {
        doNothing().when(clientStorage).addClient(clientCaptor.capture());
        bankService.registerClient(2000);
        client = clientCaptor.getValue();
        assertThat(client.getBalance()).isEqualTo(2000);
    }
    @Test
    void returnClientTest() {
        when(clientStorage.getClient(anyInt())).thenReturn(new Client(10, 100));
        bankService.printClient(1);
        verify(clientStorage).getClient(anyInt());
    }
    @Test
    void payTest() {
        operation = new Operation();
        operation.setAmount(200);
        operation.setStatus(Status.Accepted);
        when(clientStorage.getClient(anyInt())).thenReturn(new Client(0, 500));
        when(operationService.getOperation(any(), anyInt())).thenReturn(operation);
        bankService.pay(0, 300);
        assertThat(operation.getStatus()).isEqualTo(Status.Accepted);
    }
    @Test
    void payTestNoClient() {
        operation = new Operation();
        operation.setStatus(Status.Error);
        when(clientStorage.getClient(anyInt())).thenReturn(null);
        when(operationService.getOperation(any())).thenReturn(operation);
        bankService.pay(0, 300);
        assertThat(operation.getStatus()).isEqualTo(Status.Error);
    }
    @Test
    void recieveTest() {
        operation = new Operation();
        operation.setAmount(1000);
        operation.setStatus(Status.Accepted);
        when(clientStorage.getClient(anyInt())).thenReturn(new Client(0, 500));
        when(operationService.getOperation(any(), anyInt())).thenReturn(operation);
        bankService.recieve(0, 500);
        assertThat(operation.getAmount()).isEqualTo(1000);
    }
    @Test
    void recieveTestNoClient() {
        operation = new Operation();
        operation.setStatus(Status.Error);
        when(clientStorage.getClient(anyInt())).thenReturn(null);
        when(operationService.getOperation(any())).thenReturn(operation);
        bankService.recieve(0, 1000);
        assertThat(operation.getStatus()).isEqualTo(Status.Error);
    }
}