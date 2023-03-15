package com.maksimov.service;


import com.maksimov.model.BankAccount;

public interface BankAccountService {
    BankAccount addBankAccount();

    BankAccount addBankAccountWithProductLimit(double productLimit);

    BankAccount addBankAccountWithProductLimitAndWithServiceLimit(double productLimit,
                                                                  double serviceLimit);

    BankAccount addBankAccountWithServiceLimit(double serviceLimit);

    BankAccount setBankAccountProductLimit(long numberBankAccount, double productLimit);

    BankAccount setBankAccountServiceLimit(long numberBankAccount, double serviceLimit);

    BankAccount setBankAccountServiceLimitAndProductLimit(long numberBankAccount, double productLimit, double serviceLimit);
}
