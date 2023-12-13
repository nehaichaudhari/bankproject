package com.pojo;

public class Account {
	private long accountNo;
	private String accType;
	private double balance;
	private String status;
	private String date;
	private String userId;
	
	public Account() {
		
	}
	
	public Account(long accountNo, String accType, double balance, String status, String date, String userId) {
		this.accountNo=accountNo;
		this.accType=accType;
		this.balance=balance;
		this.status=status;
		this.date=date;
		this.userId=userId;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", accType=" + accType + ", balance=" + balance + ", status=" + status
				+ ", date=" + date + ", user id="+userId+ "]";
	}
}
