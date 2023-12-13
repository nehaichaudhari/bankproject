package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.pojo.Account;

public class AccountJDBCRepo {
	static Connection conn = null;
	static ResultSet rs;
	static PreparedStatement pstmt;
	static Scanner sc = new Scanner(System.in);

	public AccountJDBCRepo() {
		conn = DBConnect.getConnect();
	}

	public void getAllAccounts() {
		try {
			pstmt = conn.prepareStatement("select * from account");
			rs = pstmt.executeQuery();
			System.out.println("account details: ");
			while (rs.next()) {
				System.out.println("account No: " + rs.getLong(1) + " account type: " + rs.getString(2)
						+ " account balance: " + rs.getDouble(3) + "account status: " + rs.getString(4) + "date: "
						+ rs.getString(5) + "user id: " + rs.getString(6));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	public Account getAccountByUserId(String userId) {
		Account account = null;
		try {
			pstmt = conn.prepareStatement("select * from account where userId =?");
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				account = new Account();
				account.setAccountNo(rs.getLong(1));
				account.setAccType(rs.getString(2));
				account.setBalance(rs.getDouble(3));
				account.setStatus(rs.getString(4));
				account.setDate(rs.getString(5));
				account.setUserId(rs.getString(6));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return account;
	}

	public static void addAcount(Account account) {
		try {
			pstmt = conn.prepareStatement("insert into account values (?,?,?,?,?,?)");
			pstmt.setLong(1, account.getAccountNo());
			pstmt.setString(2, account.getAccType());
			pstmt.setDouble(3, account.getBalance());
			pstmt.setString(4, account.getStatus());
			pstmt.setString(5, account.getDate());
			pstmt.setString(6, account.getUserId());
			pstmt.executeUpdate();
			System.out.println("Account added...");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void deleteAccount() {
		System.out.println("Enter the account no to be deleted:");
		long accNo = sc.nextLong();

		try {
			pstmt = conn.prepareStatement("delete from account where accountNo=?");
			pstmt.setLong(1, accNo);

			int noOfRowsDeleted = pstmt.executeUpdate();

			if (noOfRowsDeleted > 0) {
				System.out.println("account " + accNo + " is deleted");
				getAllAccounts();
			} else {
				System.out.println("Error in deleting account...");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public boolean searchAccountByAccNo(long accNo) {
		boolean isFound = false;
		try {
			pstmt = conn.prepareStatement("select * from account where accountNo=?");
			pstmt.setLong(1, accNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				isFound = true;
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return isFound;
	}

	public void updateAccount() {
		System.out.println("Enter account no to update:");
		long accNo = sc.nextLong();

		if (searchAccountByAccNo(accNo)) {
			System.out.println("Ente the new account type:");
			String accType = sc.next();

			try {
				pstmt = conn.prepareStatement("update account set accType=? where accountNo=?");
				pstmt.setString(1, accType);
				pstmt.setLong(2, accNo);

				int noOfRowsUpdated = pstmt.executeUpdate();
				if (noOfRowsUpdated > 0) {
					System.out.println("account type updated .....");
				} else {
					System.out.println("Error in updating account type.........");
				}
			} catch (Exception e) {
				System.out.println(e);
			}

		} else {
			System.out.println("account " + accNo + " not found..");
		}
	}
}
