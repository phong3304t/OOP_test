package com.service;

import com.model.exceptions.InsufficientQuantityException;
import com.model.transactions.*;
import com.repository.*;

public class TransactionService {
    public void borrowDocument(BorrowTransaction transaction) throws InsufficientQuantityException {
        TransactionRepository.borrowDocument(transaction);
        System.out.println("Document borrowed successfully.");
    }

    public void returnDocument(ReturnTransaction transaction) {
        TransactionRepository.returnDocument(transaction);
        System.out.println("Document returned successfully.");
    }
}
