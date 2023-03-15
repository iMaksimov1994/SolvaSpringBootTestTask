package com.maksimov.service;


import com.maksimov.model.Transaction;

import java.util.HashSet;
import java.util.List;

public interface TransactionService {
    Transaction doTransaction(long accountFrom, long accountTo, String currencyShortname, double sum,
                              String expenseCategory);

    List<Transaction> getAllTransactionsGreaterLimit(long accountFrom);

    HashSet<Double> getAllLimits(long accountFrom);
}
