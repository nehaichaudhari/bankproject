package com.repo;

import java.util.HashMap;

import com.exception.AccountNotFoundException;
import com.exception.IncorrectAmountException;
import com.exception.InsufficientBalanceException;
import com.pojo.Account;

public class AccountRepository {

	private static HashMap<Long, Account> accountMap = new HashMap<>();

	public static void addAcount(long accountNo, Account account) {
		accountMap.put(accountNo, account);
	}

	public static void checkBalance(long accountNo) {
		if (accountMap.containsKey(accountNo)) {
			Account account = accountMap.get(accountNo);
			System.out.println("Balance for " + accountNo + " : " + account.getBalance());
		} else {
			throw new AccountNotFoundException(accountNo + " not found.");
		}
	}

	public static void transfer(long fromAccountNo, long toAccountNo, double amount) {
		if (amount <= 0)
			throw new IncorrectAmountException("Invalid amount : " + amount);

		if (accountMap.containsKey(fromAccountNo) && accountMap.containsKey(toAccountNo)) {

			Account fromAccount = accountMap.get(fromAccountNo);
			Account toAccount = accountMap.get(toAccountNo);

			if (amount <= fromAccount.getBalance()) {
				fromAccount.setBalance(fromAccount.getBalance() - amount);
				toAccount.setBalance(toAccount.getBalance() + amount);
				System.out.println("Amount transfered from " + fromAccountNo + " to " + toAccountNo);
			} else {
				throw new InsufficientBalanceException("Insufficient balance for : " + fromAccountNo);
			}

		} else {
			throw new AccountNotFoundException("Account not found.");
		}
	}
}
