package com.epam.davydov.selenium.data;

public class User {
	public String email;
	public String passwd;

	public User withEmail(String email) {
		this.email = email;
		return this;
	}

	public User withPasswd(String passwd) {
		this.passwd = passwd;
		return this;
	}
}