package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.pojo.Transaction;

public class TransactionJDBCRepo {
	static Connection conn = null;
	static ResultSet rs;
	static PreparedStatement pstmt;
	static Scanner sc = new Scanner(System.in);
	
	public TransactionJDBCRepo() {
		conn = DBConnect.getConnect();
	}
	
	public void addTransaction(Transaction t1) {
		try {
			pstmt = conn.prepareStatement("insert into transaction values (?,?,?,?,?,?)");
			pstmt.setInt(1, t1.getTid());
			pstmt.setString(2, t1.getTType());
			pstmt.setLong(3, t1.getAccNo());
			pstmt.setString(4, t1.getTdate());
			pstmt.setString(5, t1.getTstatus());
			pstmt.setDouble(6, t1.getTamount());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public double getBalance(long accountNo) {
		double balance= 0.00;
		try {
			pstmt = conn.prepareStatement("select sum(case when tType='Withdraw' then tamount*-1 else tamount end) as "
					+ "balance from transaction where accNo = ?");
			pstmt.setLong(1, accountNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				balance = rs.getDouble(1);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		
		return balance;
	}

}
