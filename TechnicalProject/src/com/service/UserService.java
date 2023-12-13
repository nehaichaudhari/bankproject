package com.service;

import java.util.Scanner;

import com.exception.AccountNotFoundException;
import com.exception.IncorrectAmountException;
import com.exception.InsufficientBalanceException;
import com.exception.InvalidUserException;
import com.jdbc.AccountJDBCRepo;
import com.jdbc.UserJDBCRepo;
import com.pojo.Account;
import com.pojo.Transaction;
import com.pojo.User;

public class UserService {

	static Scanner sc = new Scanner(System.in);
	public static long loginAccNo = 0;
	private static UserJDBCRepo userJDBCRepo = new UserJDBCRepo();

	static void register() {
		User user = new User();
		System.out.println("Enter the User id: ");
		user.setUserId(sc.next());
		System.out.println("Enter the password: ");
		user.setPassword(sc.next());
		System.out.println("Enter the name: ");
		user.setName(sc.next());
		System.out.println("Enter adhar card no: ");
		user.setAdharCard(sc.nextLong());
		System.out.println("Enter pan card no: ");
		user.setPanCard(sc.next());
		System.out.println("Enter email id: ");
		user.setEmail(sc.next());
		System.out.println("Enter phone no: ");
		user.setPhoneNo(sc.nextLong());
		sc.nextLine();
		System.out.println("Enter the address: ");
		user.setAddress(sc.nextLine());

//		UserRepository.addUser(accNo, user);
//		AccountRepository.addAcount(accNo, new Account());
//		System.out.println(user);
		userJDBCRepo.register(user);
	}

	static User login() {

		System.out.println("Enter User id: ");
		String userId = sc.next();
		System.out.println("Enter the passowrd: ");
		String password = sc.next();

		User user = userJDBCRepo.login(userId, password);
		if (user != null) {
			Account account = new AccountJDBCRepo().getAccountByUserId(userId);
			if (account != null) {
				loginAccNo = account.getAccountNo();
			}
			else {
				System.out.println("Account is not mapped for this account. Please create the account.");
			}
			return user;
		} else {
			throw new InvalidUserException("Invalid credentials");
		}
	}

	static void showUserMenu() {
		int choice = 0;
		while (choice != 6) {
			System.out.println("-------------------------------------------------------------");
			System.out.println("1. Add Account");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Check Balance");
			System.out.println("5. Transfer");
			System.out.println("6. Logout");
			System.out.println("-------------------------------------------------------------");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				AccountService.addAccount();
				break;
			case 2:
				System.out.println("Deposit amount: ");
				double amount = sc.nextDouble();

				try {
					Transaction transaction = new Transaction(0,"Deposit",UserService.loginAccNo,"","",amount);
					TransactionService.deposit(transaction);
				} catch (AccountNotFoundException | IncorrectAmountException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Withdraw amount: ");
				double amt = sc.nextDouble();
				try {
					Transaction transaction = new Transaction(0,"Withdraw",UserService.loginAccNo,"","",amt);
					TransactionService.withdraw(transaction);
				} catch (AccountNotFoundException | InsufficientBalanceException | IncorrectAmountException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				AccountService.checkBalance(UserService.loginAccNo);
				break;
			case 5:
				System.out.println("Enter to account no: ");
				long toAccountNo = sc.nextLong();

				System.out.println("Transfer amount: ");
				double transferAmount = sc.nextDouble();
				try {
					Transaction t1 = new Transaction (0,"Withdraw",UserService.loginAccNo,"","",transferAmount);
					Transaction t2 = new Transaction (0,"Deposit",toAccountNo,"","",transferAmount);
//					AccountService.transfer(UserService.loginAccNo, toAccountNo, transferAmount);
					TransactionService.transfer(t1, t2);
				} catch (AccountNotFoundException | InsufficientBalanceException | IncorrectAmountException e) {
					System.out.println(e.getMessage());
				}
				break;

			default:
				break;
			}
		}
	}
}
