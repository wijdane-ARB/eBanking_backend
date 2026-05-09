package org.sid.ebanking_backend.serices;

import org.sid.ebanking_backend.entities.BankAccount;
import org.sid.ebanking_backend.entities.CurrentAccount;
import org.sid.ebanking_backend.entities.SavingAccount;
import org.sid.ebanking_backend.repositories.BankAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepo bankAccountRepo;
    public void consulter(){
        BankAccount bankAccount=
                 bankAccountRepo.findById("0b36be78-8d5d-446b-9f20-37eadc9d3c3b").orElse(null);        if(bankAccount!=null) {
            System.out.println("*****************************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());
            if (bankAccount instanceof CurrentAccount) {
                System.out.println("Over Draft=>" + ((CurrentAccount) bankAccount).getOverDraft());
            } else if (bankAccount instanceof SavingAccount) {
                System.out.println("Rate=>" + ((SavingAccount) bankAccount).getInterestRate());
            }
            bankAccount.getAccountOperations().forEach(op -> {
                System.out.println(op.getType() + "\t" + op.getOperationDate() + "\t" + op.getAmount());
            });
        }
    }
}
