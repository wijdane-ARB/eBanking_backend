package org.sid.ebanking_backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.ebanking_backend.entities.BankAccount;

//import javax.persistence.*;
import java.util.List;

@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
}
