package com.service;

import java.util.Scanner;

import com.jdbc.AccountJDBCRepo;
import com.jdbc.TransactionJDBCRepo;
import com.pojo.Account;
import com.pojo.Transaction;
import com.repo.AccountRepository;

public class AccountService {

	static AccountJDBCRepo accountJDBCRepo = new AccountJDBCRepo();

	static void addAccount() {
		Account account = new Account();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter account no: ");
		account.setAccountNo(sc.nextLong());
		System.out.println("Enter account type: ");
		account.setAccType(sc.next());
		System.out.println("Enter balance: ");
		account.setBalance(sc.nextDouble());
		System.out.println("Enter status: ");
		account.setStatus(sc.next());
		System.out.println("Enter date: ");
		account.setDate(sc.next());
		System.out.println("Enter user id: ");
		account.setUserId(sc.next());

//		AccountRepository.addAcount(account.getAccountNo(), account);
		accountJDBCRepo.addAcount(account);
		Transaction t1 = new Transaction();
		t1.setAccNo(account.getAccountNo());
		t1.setTamount(account.getBalance());
		t1.setTType("Deposit");
		new TransactionJDBCRepo().addTransaction(t1);

	}

	static void checkBalance(long accountNo) {
//		AccountRepository.checkBalance(accountNo);
		double balance = new TransactionJDBCRepo().getBalance(accountNo);
		System.out.println("Balance : "+balance);
	}

}
