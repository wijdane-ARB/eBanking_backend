package org.sid.ebanking_backend;

import org.sid.ebanking_backend.entities.CurrentAccount;
import org.sid.ebanking_backend.entities.Customer;
import org.sid.ebanking_backend.entities.SavingAccount;
import org.sid.ebanking_backend.enums.AccountStatus;
import org.sid.ebanking_backend.repositories.AccountOperationRepo;
import org.sid.ebanking_backend.repositories.BankAccountRepo;
import org.sid.ebanking_backend.repositories.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EBankingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EBankingBackendApplication.class, args);
	}


	@Bean
	CommandLineRunner start(CustomerRepo customerRepo, BankAccountRepo  bankAccountRepo, AccountOperationRepo accountOperationRepo){
		return args -> {
			Stream.of("Hassan","Yassin","Aicha").forEach(name -> {
				Customer customer = new Customer();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				customerRepo.save(customer);

			});
			customerRepo.findAll().forEach(cust->{
				CurrentAccount currentAccount = new CurrentAccount();
				currentAccount.setId(UUID.randomUUID().toString());
				currentAccount.setBalance(Math.random()*90000);
				currentAccount.setCreatedAt(new Date());
				currentAccount.setStatus(AccountStatus.CREATED);
				currentAccount.setCustomer(cust);
				currentAccount.setOverDraft(9000);
				bankAccountRepo.save(currentAccount);


				SavingAccount savingAccount = new SavingAccount();
				savingAccount.setBalance(Math.random()*90000);
				savingAccount.setCreatedAt(new Date());
				savingAccount.setStatus(AccountStatus.CREATED);
				savingAccount.setCustomer(cust);
				savingAccount.setInterestRate(5.5);
				bankAccountRepo.save(currentAccount);
			});

		};
	}

}
