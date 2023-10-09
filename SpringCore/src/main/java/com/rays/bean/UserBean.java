package com.rays.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("user")
public class UserBean {

	private String name = null;
	private String login = null;
	private String password = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}