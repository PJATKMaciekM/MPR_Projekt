package pl.pjatk.s32006Bank;

import org.springframework.stereotype.Component;

@Component
public class OperationService {
    public Operation getOperation(Status status, int amount) {
        Operation operation = new Operation();
        operation.setAmount(amount);
        operation.setStatus(status);
        return operation;
    }
    public Operation getOperation(Status status) {
        Operation operation = new Operation();
        operation.setStatus(status);
        return operation;
    }
}
