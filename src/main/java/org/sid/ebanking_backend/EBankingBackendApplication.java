package org.sid.ebanking_backend;

import org.sid.ebanking_backend.entities.*;
import org.sid.ebanking_backend.enums.AccountStatus;
import org.sid.ebanking_backend.enums.OperationType;
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
				savingAccount.setId(UUID.randomUUID().toString());
				savingAccount.setBalance(Math.random()*90000);
				savingAccount.setCreatedAt(new Date());
				savingAccount.setStatus(AccountStatus.CREATED);
				savingAccount.setCustomer(cust);
				savingAccount.setInterestRate(5.5);
				bankAccountRepo.save(savingAccount);
			});

			bankAccountRepo.findAll().forEach(acc-> {
						for (int i = 0; i < 10; i++) {
							AccountOperation accountOperation = new AccountOperation();
							accountOperation.setOperationDate(new Date());
							accountOperation.setAmount(Math.random() * 12000);
							accountOperation.setType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
							accountOperation.setBankAccount(acc);
							accountOperationRepo.save(accountOperation);

						}
					});
				BankAccount bankAccount = bankAccountRepo.findById("1029115c-6c1e-45ac-bf0f-08d8aeea057c").orElse(null);
				System.out.println("***********************");
				System.out.println(bankAccount.getId());
				System.out.println(bankAccount.getBalance());
				System.out.println(bankAccount.getCreatedAt());
				System.out.println(bankAccount.getStatus());
				System.out.println(bankAccount.getCustomer().getName());
				System.out.println(bankAccount.getClass().getSimpleName());
				if(bankAccount instanceof CurrentAccount){
					System.out.println("Over Draft==>"+((CurrentAccount)bankAccount).getOverDraft());

				} else if (bankAccount instanceof SavingAccount) {
					System.out.println("Rate==>"+((SavingAccount)bankAccount).getInterestRate());

				}
				bankAccount.getAccountOperations().forEach(op->{
					System.out.println("=============================");
					System.out.println(op.getType());
					System.out.println(op.getAmount());
					System.out.println(op.getOperationDate());

				}
				);

			};

		};
	}


