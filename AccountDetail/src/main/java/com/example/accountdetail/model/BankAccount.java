package com.example.accountdetail.model;

import com.example.accountdetail.enumeration.ACCOUNT_TYPE;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BankAccount extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Long accountNumber;

    @Column(nullable = false)
    @Size(min = 4,max = 4,message = "pin must be four digits")
    private Long pin;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ACCOUNT_TYPE account_type;

    private BigDecimal accountBalance = BigDecimal.valueOf(0);

}
