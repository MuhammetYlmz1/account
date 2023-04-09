package com.muhammet.account.service;

import com.muhammet.account.model.Account;
import com.muhammet.account.model.Transaction;
import com.muhammet.account.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

    private Logger logger=LoggerFactory.getLogger(Transaction.class);
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    protected Transaction initiateMoney(final Account account, BigDecimal amount){
        return transactionRepository.save(
          new Transaction(amount,account)
        );
    }

}
