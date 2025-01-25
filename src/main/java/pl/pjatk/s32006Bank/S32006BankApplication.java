package pl.pjatk.s32006Bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class S32006BankApplication {
	private BankService bankService;
	public S32006BankApplication(BankService bankService) {
		this.bankService = bankService;
		System.out.println("S32006 Bank Application Started");
//		bankService.registerClient(3000);
//		bankService.registerClient(4000);
//		bankService.pay(0, 500);
//		bankService.printClient(1);
	}

	public static void main(String[] args) {
		SpringApplication.run(S32006BankApplication.class, args);
	}

}
