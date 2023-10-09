package com.rays.bean;

import org.springframework.stereotype.Component;

@Component("userDetails")
public class UserDetails {
	
	private String mob=null;
	private String address=null;
	private String email=null; 
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
