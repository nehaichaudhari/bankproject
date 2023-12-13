package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.pojo.User;

public class UserJDBCRepo {
	static Connection conn = null;
	static ResultSet rs;
	static PreparedStatement pstmt;
	static Scanner sc = new Scanner(System.in);

	public UserJDBCRepo() {
		conn = DBConnect.getConnect();
	}
	
	public static void register(User user) {
		conn = DBConnect.getConnect();
		try {
			pstmt = conn.prepareStatement("insert into user values (?,?,?,?,?,?,?,?)");
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setLong(4, user.getAdharCard());
			pstmt.setString(5, user.getPanCard());
			pstmt.setString(6, user.getEmail());
			pstmt.setLong(7, user.getPhoneNo());
			pstmt.setString(8, user.getAddress());
			pstmt.executeUpdate();
			System.out.println("User added...");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public User login(String userId, String password) {
		User user = null;
		try {
			pstmt = conn.prepareStatement("select * from user where userId=? and password=?");
			pstmt.setString(1, userId);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				user = new User();
				user.setUserId(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setName(rs.getString(3));
				user.setAdharCard(rs.getLong(4));
				user.setPanCard(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setPhoneNo(rs.getLong(7));
				user.setAddress(rs.getString(8));
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return user;
	}

}
