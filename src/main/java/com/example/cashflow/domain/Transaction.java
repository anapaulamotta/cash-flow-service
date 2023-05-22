package com.example.cashflow.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(generator = "transaction_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "transaction_seq", sequenceName = "transaction_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    private String type;
    private String description;
    private BigDecimal value;
    private LocalDateTime dateTime;

}
