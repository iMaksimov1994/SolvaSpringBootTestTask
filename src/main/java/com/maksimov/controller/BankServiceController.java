package com.maksimov.controller;

import com.maksimov.model.BankAccount;
import com.maksimov.model.Transaction;
import com.maksimov.service.BankAccountService;
import com.maksimov.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank_service")
public class BankServiceController {
    private TransactionService transactionService;
    private BankAccountService bankAccountService;

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Autowired
    public void setBankAccountService(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/doTransaction")
    public ResponseEntity doTransaction(@RequestParam long accountFrom, @RequestParam long accountTo,
                                        @RequestParam String currencyShortname, @RequestParam double sum,
                                        @RequestParam String expenseCategory) {
        Transaction transaction = this.transactionService.doTransaction(accountFrom, accountTo, currencyShortname,
                sum, expenseCategory);
        if (transaction != null) {
            return new ResponseEntity(transaction, HttpStatus.OK);
        } else {
            return new ResponseEntity("Transaction failed!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addBankAccount")
    public ResponseEntity addBankAccount() {
        try {
            return new ResponseEntity(this.bankAccountService.addBankAccount(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addBankAccountWithProductLimit")
    public ResponseEntity addBankAccountWithProductLimit(@RequestParam double productLimit) {
        try {
            return new ResponseEntity(this.bankAccountService.addBankAccountWithProductLimit(productLimit), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addBankAccountWithServiceLimit")
    public ResponseEntity addBankAccountWithServiceLimit(@RequestParam double serviceLimit) {
        try {
            return new ResponseEntity(this.bankAccountService.addBankAccountWithServiceLimit(serviceLimit), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addBankAccountWithProductLimitAndWithServiceLimit")
    public ResponseEntity addBankAccountWithProductLimitAndWithServiceLimit(@RequestParam double productLimit,
                                                                            @RequestParam double serviceLimit) {
        try {
            return new ResponseEntity(
                    this.bankAccountService.addBankAccountWithProductLimitAndWithServiceLimit(productLimit,
                    serviceLimit), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/setBankAccountProductLimit")
    public ResponseEntity setBankAccountProductLimit(@RequestParam long numberBankAccount, @RequestParam double productLimit) {
        BankAccount bankAccount = this.bankAccountService.setBankAccountProductLimit(numberBankAccount, productLimit);
        if (bankAccount != null) {
            return new ResponseEntity(bankAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity("Bank account with this number not found!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/setBankAccountServiceLimit")
    public ResponseEntity setBankAccountServiceLimit(@RequestParam long numberBankAccount, @RequestParam double serviceLimit) {
        BankAccount bankAccount = this.bankAccountService.setBankAccountServiceLimit(numberBankAccount, serviceLimit);
        if (bankAccount != null) {
            return new ResponseEntity(bankAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity("Bank account with this number not found!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/setBankAccountServiceLimitAndProductLimit")
    public ResponseEntity setBankAccountServiceLimitAndProductLimit(@RequestParam long numberBankAccount,
                                                                    @RequestParam double productLimit,
                                                                    @RequestParam double serviceLimit) {
        BankAccount bankAccount = this.bankAccountService.setBankAccountServiceLimitAndProductLimit(numberBankAccount,
                productLimit, serviceLimit);
        if (bankAccount != null) {
            return new ResponseEntity(bankAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity("Bank account with this number not found!", HttpStatus.BAD_REQUEST);
        }
    }
}
