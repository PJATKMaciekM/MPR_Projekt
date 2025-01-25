package pl.pjatk.s32006Bank;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientStorage {
    private List<Client> clients;
    public ClientStorage() {
        clients = new ArrayList<Client>();
    }
    public void addClient(Client client) {
        clients.add(client);
    }
    public void removeClient(Client client) {
        clients.remove(client);
    }
    public List<Client> getClients() {
        return clients;
    }
    public Client getClient(int id) {
        for (Client client : clients) {
            if (client.getId() == id) {
                return client;
            }
        }
        return null;
    }

}
