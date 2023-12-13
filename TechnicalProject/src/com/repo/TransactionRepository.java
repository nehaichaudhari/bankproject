package com.repo;

import java.util.HashMap;

import com.exception.AccountNotFoundException;
import com.exception.IncorrectAmountException;
import com.exception.InsufficientBalanceException;
import com.pojo.Account;
import com.pojo.Transaction;

public class TransactionRepository {
	
	private static HashMap<Long, Account> accountMap = new HashMap<>();

	public static void addAcount(long accountNo, Account account) {
		accountMap.put(accountNo, account);
	}
	
	public static void transact(Transaction transaction) {
		if (transaction.getTType().equals("Deposit")) {
			try {
				deposit(transaction.getAccNo(), transaction.getTamount());
				transaction.setTstatus("Completed");
			}
			catch (Exception ex) {
				throw ex;
			}
		}
		else if (transaction.getTType().equals("Withdraw")) {
			try {
				withdraw(transaction.getAccNo(), transaction.getTamount());
				transaction.setTstatus("Completed");
			}
			catch (Exception ex) {
				throw ex;
			}
		}
	}
	
	private static void deposit(long accountNo, double amount) {
		if (amount <= 0)
			throw new IncorrectAmountException("Invalid amount : " + amount);

		if (accountMap.containsKey(accountNo)) {
			Account account = accountMap.get(accountNo);
			account.setBalance(account.getBalance() + amount);
			System.out.println("Amount deposited to account : " + accountNo);
		} else {
			throw new AccountNotFoundException(accountNo + " not found.");
		}
	}

	private static void withdraw(long accountNo, double amount) {
		if (amount <= 0)
			throw new IncorrectAmountException("Invalid amount : " + amount);

		if (accountMap.containsKey(accountNo)) {
			Account account = accountMap.get(accountNo);
			if (amount <= account.getBalance()) {
				account.setBalance(account.getBalance() - amount);
				System.out.println("Amount withdrew from account : " + accountNo);
			} else {
				throw new InsufficientBalanceException("Insufficient balance for : " + accountNo);
			}
		} else {
			throw new AccountNotFoundException(accountNo + " not found.");
		}
	}
	
	public static void transfer(Transaction from, Transaction to) {
		double amount = from.getTamount();
		if (amount <= 0)
			throw new IncorrectAmountException("Invalid amount : " + amount);

		if (accountMap.containsKey(from.getAccNo()) && accountMap.containsKey(to.getAccNo())) {

			Account fromAccount = accountMap.get(from.getAccNo());
			Account toAccount = accountMap.get(to.getAccNo());

			if (amount <= fromAccount.getBalance()) {
				fromAccount.setBalance(fromAccount.getBalance() - amount);
				toAccount.setBalance(toAccount.getBalance() + amount);
				System.out.println("Amount transfered from " + fromAccount.getAccountNo() + " to " + toAccount.getAccountNo());
			} else {
				throw new InsufficientBalanceException("Insufficient balance for : " + fromAccount.getAccountNo());
			}

		} else {
			throw new AccountNotFoundException("Account not found.");
		}
	}
}
