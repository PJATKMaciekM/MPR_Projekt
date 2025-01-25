package pl.pjatk.s32006Bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class S32006BankApplication {
	private BankService bankService;
	public S32006BankApplication(BankService bankService) {
		this.bankService = bankService;
		System.out.println("S32006 Bank Application Started");
	}

	public static void main(String[] args) {
		SpringApplication.run(S32006BankApplication.class, args);
	}

}
