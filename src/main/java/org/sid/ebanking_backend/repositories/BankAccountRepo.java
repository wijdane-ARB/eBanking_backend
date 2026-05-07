package org.sid.ebanking_backend.repositories;

import org.sid.ebanking_backend.entities.BankAccount;
import org.sid.ebanking_backend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepo extends JpaRepository<BankAccount,String> {
}
