package com.maksimov.service;

import com.maksimov.model.BankAccount;
import com.maksimov.model.Currency;
import com.maksimov.model.Transaction;
import com.maksimov.repository.BankAccountRepository;
import com.maksimov.repository.CurrencyRepository;
import com.maksimov.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    private BankAccountRepository bankAccountRepository;
    private TransactionRepository transactionRepository;
    private CurrencyRepository currencyRepository;

    @Autowired
    public void setBankAccountRepository(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Autowired
    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Autowired
    public void setCurrencyRepository(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Transaction doTransaction(long accountFrom, long accountTo, String currencyShortname, double sum, String expenseCategory) {

        BankAccount bankAccountByNumberBankAccount = this.bankAccountRepository.findBankAccountById(accountFrom);
        if (bankAccountByNumberBankAccount != null) {
            Transaction transaction = new Transaction(accountTo, currencyShortname, sum, expenseCategory,
                    bankAccountByNumberBankAccount);
            if (currencyShortname.equals("USD")) {
                bankAccountByNumberBankAccount.doTransactionUSD(transaction);
                this.bankAccountRepository.save(bankAccountByNumberBankAccount);
                this.transactionRepository.save(transaction);
                return transaction;
            }
            if (currencyShortname.equals("RUB")) {
                bankAccountByNumberBankAccount.doTransactionRUB(transaction, returnActualCurrency());
                this.bankAccountRepository.save(bankAccountByNumberBankAccount);
                this.transactionRepository.save(transaction);
                return transaction;
            }
        }
        return null;
    }

    @Override
    public List<Transaction> getAllTransactionsGreaterLimit(long accountFrom) {
        return this.transactionRepository.getTransactionByBankAccountId(accountFrom).stream().
                filter(Transaction::isLimitExceeded).collect(Collectors.toList());
    }

    @Override
    public HashSet<Double> getAllLimits(long accountFrom) {
        return (HashSet<Double>) this.transactionRepository.getTransactionByBankAccountId(accountFrom).stream().
                map(Transaction::getLimitSum).collect(Collectors.toSet());
    }

    private double returnActualCurrency() {
        LocalDateTime actualDateTime = LocalDateTime.MIN;
        for (Currency currency : this.currencyRepository.findAll()) {
            if (currency.getDatetime().isAfter(actualDateTime)) {
                actualDateTime = currency.getDatetime();
            }
        }
        for (Currency currency : this.currencyRepository.findAll()) {
            if (currency.getDatetime().isEqual(actualDateTime)) {
                return currency.getClose();
            }
        }
        return 1;
    }
}




