package com.pojo;

public class User {
	private String userId;
	private String password;
	private String name;
	private long adharCard;
	private String panCard;
	private String email;
	private long phoneNo;
	private String address;

	public User() {

	}

	public User(String userId, String password, String name, long adharCard, String panCard, String email, long phoneNo,
			String address) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.adharCard = adharCard;
		this.panCard = panCard;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAdharCard() {
		return adharCard;
	}

	public void setAdharCard(long adharCard) {
		this.adharCard = adharCard;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [ User id=" + userId + ",password=" + password + ", name=" + name + ", adharCard=" + adharCard
				+ ", panCard=" + panCard + ", email=" + email + ", phoneNo=" + phoneNo + ", address=" + address + "]";
	}

}
