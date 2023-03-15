package com.maksimov.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bank_account")
@Data
@NoArgsConstructor
public class BankAccount {
    @Id
    @Column(name = "number_bank_account")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_limit")
    private double productLimit = 0;

    @Column(name = "service_limit")
    private double serviceLimit = 0;

    @Column(name = "product_limit_datetime", unique = true)
    private LocalDateTime productLimitDatetime;

    @Column(name = "service_limit_datetime", unique = true)
    private LocalDateTime serviceLimitDatetime;

    @Column(name = "product_limit_remainder_of_Limit")
    private double productLimitRemainderOfLimit = productLimit;

    @Column(name = "service_limit_remainder_of_Limit")
    private double serviceLimitRemainderOfLimit = serviceLimit;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bankAccount")
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE)
    private List<Transaction> transactions = new ArrayList<>();

    public void doTransactionUSD(Transaction transaction) {
        String expenseCategory = transaction.getExpenseCategory();
        if (expenseCategory.equals("product")) {
            transaction.setDatetime(LocalDateTime.now());
            this.productLimitRemainderOfLimit = this.productLimitRemainderOfLimit - transaction.getSum();
            transaction.setLimitSum(this.productLimit);
            transaction.setLimitDatetime(this.productLimitDatetime);
            transaction.setLimitCurrencyShortname("USD");
            if (this.productLimitRemainderOfLimit < 0) {
                transaction.setLimitExceeded(true);
            }
        }
        if (expenseCategory.equals("service")) {
            transaction.setDatetime(LocalDateTime.now());
            this.serviceLimitRemainderOfLimit = this.serviceLimitRemainderOfLimit - transaction.getSum();
            transaction.setLimitSum(this.serviceLimit);
            transaction.setLimitDatetime(this.serviceLimitDatetime);
            transaction.setLimitCurrencyShortname("USD");
            if (this.serviceLimitRemainderOfLimit < 0) {
                transaction.setLimitExceeded(true);
            }
        }
    }

    public void doTransactionRUB(Transaction transaction, double currencyRubUsd) {
        String expenseCategory = transaction.getExpenseCategory();
        if (expenseCategory.equals("product")) {
            transaction.setDatetime(LocalDateTime.now());
            this.productLimitRemainderOfLimit = this.productLimitRemainderOfLimit - transaction.getSum() * currencyRubUsd;
            transaction.setLimitSum(this.productLimit /*/ currencyRubUsd*/);
            transaction.setLimitDatetime(this.productLimitDatetime);
            transaction.setLimitCurrencyShortname("USD");
            if (this.productLimitRemainderOfLimit < 0) {
                transaction.setLimitExceeded(true);
            }
        }
        if (expenseCategory.equals("service")) {
            transaction.setDatetime(LocalDateTime.now());
            this.serviceLimitRemainderOfLimit = this.serviceLimitRemainderOfLimit - transaction.getSum() * currencyRubUsd;
            transaction.setLimitSum(this.serviceLimit /*/ currencyRubUsd*/);
            transaction.setLimitDatetime(this.serviceLimitDatetime);
            transaction.setLimitCurrencyShortname("USD");
            if (this.serviceLimitRemainderOfLimit < 0) {
                transaction.setLimitExceeded(true);
            }
        }
    }
}
