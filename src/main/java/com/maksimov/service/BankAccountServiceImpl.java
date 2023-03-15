package com.maksimov.service;

import com.maksimov.model.BankAccount;
import com.maksimov.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {
    private BankAccountRepository bankAccountRepository;

    @Autowired
    public void setBankAccountRepository(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public BankAccount addBankAccount() {
        try {
            return this.bankAccountRepository.save(new BankAccount());
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Bank account with this number already exists!");
        }
    }

    @Override
    public BankAccount addBankAccountWithProductLimit(double productLimit) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setProductLimit(productLimit);
        bankAccount.setProductLimitRemainderOfLimit(productLimit);
        bankAccount.setProductLimitDatetime(LocalDateTime.now());
        try {
            return this.bankAccountRepository.save(bankAccount);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Bank account with this number already exists!");
        }
    }

    @Override
    public BankAccount addBankAccountWithServiceLimit(double serviceLimit) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setServiceLimit(serviceLimit);
        bankAccount.setServiceLimitRemainderOfLimit(serviceLimit);
        bankAccount.setServiceLimitDatetime(LocalDateTime.now());
        try {
            return this.bankAccountRepository.save(bankAccount);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Bank account with this number already exists!");
        }
    }

    @Override
    public BankAccount addBankAccountWithProductLimitAndWithServiceLimit(
            double productLimit, double serviceLimit) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setProductLimit(productLimit);
        bankAccount.setServiceLimit(serviceLimit);
        bankAccount.setProductLimitRemainderOfLimit(productLimit);
        bankAccount.setServiceLimitRemainderOfLimit(serviceLimit);
        bankAccount.setProductLimitDatetime(LocalDateTime.now());
        bankAccount.setServiceLimitDatetime(LocalDateTime.now());
        try {
            return this.bankAccountRepository.save(bankAccount);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Bank account with this number already exists!");
        }
    }

    @Override
    public BankAccount setBankAccountProductLimit(long numberBankAccount, double productLimit) {
        BankAccount bankAccountByNumberBankAccount = this.bankAccountRepository.
                findBankAccountById(numberBankAccount);
        if (bankAccountByNumberBankAccount != null) {
            bankAccountByNumberBankAccount.setProductLimit(productLimit);
            bankAccountByNumberBankAccount.setProductLimitRemainderOfLimit(productLimit);
            bankAccountByNumberBankAccount.setProductLimitDatetime(LocalDateTime.now());
            return this.bankAccountRepository.save(bankAccountByNumberBankAccount);
        }
        return null;
    }

    @Override
    public BankAccount setBankAccountServiceLimit(long numberBankAccount, double serviceLimit) {
        BankAccount bankAccountByNumberBankAccount = this.bankAccountRepository.
                findBankAccountById(numberBankAccount);
        if (bankAccountByNumberBankAccount != null) {
            bankAccountByNumberBankAccount.setServiceLimit(serviceLimit);
            bankAccountByNumberBankAccount.setServiceLimitRemainderOfLimit(serviceLimit);
            bankAccountByNumberBankAccount.setServiceLimitDatetime(LocalDateTime.now());
            return this.bankAccountRepository.save(bankAccountByNumberBankAccount);
        }
        return null;
    }


    @Override
    public BankAccount setBankAccountServiceLimitAndProductLimit(long numberBankAccount, double productLimit,
                                                                 double serviceLimit) {
        BankAccount bankAccountByNumberBankAccount = this.bankAccountRepository.
                findBankAccountById(numberBankAccount);
        if (bankAccountByNumberBankAccount != null) {
            bankAccountByNumberBankAccount.setProductLimit(productLimit);
            bankAccountByNumberBankAccount.setProductLimitRemainderOfLimit(productLimit);
            bankAccountByNumberBankAccount.setServiceLimit(serviceLimit);
            bankAccountByNumberBankAccount.setServiceLimitRemainderOfLimit(serviceLimit);
            bankAccountByNumberBankAccount.setServiceLimitDatetime(LocalDateTime.now());
            bankAccountByNumberBankAccount.setServiceLimitDatetime(LocalDateTime.now());
            return this.bankAccountRepository.save(bankAccountByNumberBankAccount);
        }
        return null;
    }
}
