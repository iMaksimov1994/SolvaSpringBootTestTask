package com.maksimov.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @NonNull
    @Column(name = "account_to")
    private long accountTo;

    @NonNull
    @Column(name = "currency_shortname")
    private String currencyShortname;

    @NonNull
    @Column(name = "sum")
    private double sum;

    @NonNull
    @Column(name = "expense_category")
    private String expenseCategory;


    @Column(name = "datetime", unique = true)
    private LocalDateTime datetime;

    @Column(name = "limit_sum")
    private double limitSum;

    @Column(name = "limit_datetime")
    private LocalDateTime limitDatetime;

    @Column(name = "limit_currency_shortname")
    private String limitCurrencyShortname;

    @Column(name = "limit_exceeded")
    private boolean limitExceeded;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "bank_account_id", nullable = false)
    private BankAccount bankAccount;
}
