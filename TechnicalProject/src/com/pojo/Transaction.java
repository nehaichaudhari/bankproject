package com.pojo;

public class Transaction {
	private int tid;
	private String tType;
	private long accNo;
	private String tdate;
	private String tstatus;
	private double tamount;
	
	public Transaction() {
		
	}
	
	public Transaction(int tid, String tType, long accNo, String tdate, String tstatus, double tamount) {
		this.tid=tid;
		this.tType=tType;
		this.accNo=accNo;
		this.tdate=tdate;
		this.tstatus=tstatus;
		this.tamount=tamount;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTType() {
		return tType;
	}

	public void setTType(String tType) {
		this.tType = tType;
	}

	public long getAccNo() {
		return accNo;
	}

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}

	public String getTdate() {
		return tdate;
	}

	public void setTdate(String tdate) {
		this.tdate = tdate;
	}

	public String getTstatus() {
		return tstatus;
	}

	public void setTstatus(String tstatus) {
		this.tstatus = tstatus;
	}

	public double getTamount() {
		return tamount;
	}

	public void setTamount(double tamount) {
		this.tamount = tamount;
	}

	@Override
	public String toString() {
		return "Transaction [transaction id= "+tid+", transactionType=" + tType + ", accNo=" + accNo + ", tdate=" + tdate
				+ ", tstatus=" + tstatus + ", tamount=" + tamount + "]";
	}
}
