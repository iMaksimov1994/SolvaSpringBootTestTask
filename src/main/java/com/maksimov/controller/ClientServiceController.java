package com.maksimov.controller;

import com.maksimov.model.Transaction;
import com.maksimov.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/client_service")
public class ClientServiceController {
    private TransactionService transactionService;

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/getAllTransactionsGreaterLimit")
    public ResponseEntity getAllTransactionsGreaterLimit(@RequestParam long accountFrom) {
        List<Transaction> allTransactionsGreaterLimit = this.transactionService.getAllTransactionsGreaterLimit(accountFrom);
        if (allTransactionsGreaterLimit.size() != 0) {
            return new ResponseEntity(allTransactionsGreaterLimit, HttpStatus.OK);
        } else {
            return new ResponseEntity("List of transactions exceeding the limit is empty!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllLimits")
    public ResponseEntity getAllLimits(@RequestParam long accountFrom) {
        HashSet<Double> allLimits = this.transactionService.getAllLimits(accountFrom);
        if (allLimits.size() != 0) {
            return new ResponseEntity(allLimits, HttpStatus.OK);
        } else {
            return new ResponseEntity("List of all limits is empty!", HttpStatus.BAD_REQUEST);
        }
    }
}
