package com.rays.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UserForm {

	protected long id = 0;

	
	@NotNull(message="first name is required")
	private String firstName;

	
	@NotEmpty(message="last name is required")
	@Pattern(regexp = "^[a-zA-Z0-9]{6,12}$",
    message = "last name must be of 6 to 12 length with no special characters")
	private String lastName;

	@NotEmpty(message="username is required")
	@Email(message="Email should be valid")
	private String login;

	@NotEmpty
	private String password;

	private String dob;

	@NotEmpty
	private String address;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
