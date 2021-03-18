package it.engineering.spring.mvc.dto;

import java.io.Serializable;


public class UserDto implements Serializable{
	private static final long serialVersionUID = 16032021123500L;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	
	public UserDto() {
		// TODO Auto-generated constructor stub
	}

	public UserDto(String firstname, String lastname, String password, String username) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserDto [firstname=" + firstname + ", lastname=" + lastname + ", username=" + username + ", password="
				+ password + "]";
	}
	
}
