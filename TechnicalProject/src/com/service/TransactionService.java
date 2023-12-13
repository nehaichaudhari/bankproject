package com.service;

import com.exception.AccountNotFoundException;
import com.exception.IncorrectAmountException;
import com.exception.InsufficientBalanceException;
import com.jdbc.AccountJDBCRepo;
import com.jdbc.TransactionJDBCRepo;
import com.pojo.Transaction;

public class TransactionService {
	
	private static TransactionJDBCRepo transactionJDBCRepo = new TransactionJDBCRepo();
	
	public static void deposit(Transaction transaction) {
		if (transaction.getTamount() <= 0)
			throw new IncorrectAmountException("Invalid amount : " + transaction.getTamount());

		transactionJDBCRepo.addTransaction(transaction);
	}
	
	public static void withdraw(Transaction transaction) {
		if (transaction.getTamount() <= 0)
			throw new IncorrectAmountException("Invalid amount : " + transaction.getTamount());
		if (transaction.getTamount() <= transactionJDBCRepo.getBalance(transaction.getAccNo())) {
			transactionJDBCRepo.addTransaction(transaction);
		} else {
			throw new InsufficientBalanceException("Insufficient balance");
		}
	}
	
	public static void transfer(Transaction t1, Transaction t2) {
		double amount = t1.getTamount();
		if (amount <= 0)
			throw new IncorrectAmountException("Invalid amount : " + amount);

		if (new AccountJDBCRepo().searchAccountByAccNo(t2.getAccNo())) {
			if (amount <= transactionJDBCRepo.getBalance(t1.getAccNo())) {
				transactionJDBCRepo.addTransaction(t1);
				transactionJDBCRepo.addTransaction(t2);
			} else {
				throw new InsufficientBalanceException("Insufficient balance.");
			}

		} else {
			throw new AccountNotFoundException("Account not found.");
		}
		//TransactionRepository.transfer(t1, t2);
	}
}
