package pl.pjatk.s32006Bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Equals;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Component;
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
}
