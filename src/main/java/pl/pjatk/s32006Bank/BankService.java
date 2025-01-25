package pl.pjatk.s32006Bank;

import org.springframework.stereotype.Component;

@Component
public class BankService {
    private final ClientStorage clientStorage;
    private final OperationService operationService;
    public BankService(ClientStorage clientStorage, OperationService operationService) {
        this.clientStorage = clientStorage;
        this.operationService = operationService;
    }
    public Operation pay(int id, int amount) {
        Client client = clientStorage.getClient(id);
        if(client == null) {
            return operationService.getOperation(Status.Error);
        }
        if(client.getBalance() < amount) {
            return operationService.getOperation(Status.Declined, client.getBalance());
        }
        client.setBalance(client.getBalance() - amount);
        return operationService.getOperation(Status.Accepted, client.getBalance());
    }
    public Operation recieve(int id, int amount) {
        Client client = clientStorage.getClient(id);
        if(client == null) {
            return operationService.getOperation(Status.Error);
        }
        client.setBalance(client.getBalance() + amount);
        return operationService.getOperation(Status.Accepted, client.getBalance());
    }
    public void registerClient(int amount) {
        int id = clientStorage.getClients().size();
        Client client = new Client(id, amount);
        clientStorage.addClient(client);
    }
    public void printClient(int id) {
        Client client = clientStorage.getClient(id);
        if(client == null) {
            System.out.println("Client not found");
        }else {
            System.out.println("Id: " + client.getId() + ", Balance: " + client.getBalance());
        }
    }
}
