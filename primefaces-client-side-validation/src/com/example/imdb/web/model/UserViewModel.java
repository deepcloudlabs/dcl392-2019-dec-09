package com.example.imdb.web.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.example.web.validation.Email;
import com.example.web.validation.Iban;
import com.example.web.validation.StrongPassword;
import com.example.web.validation.TcKimlikNo;
import com.example.web.validation.Username;

@SuppressWarnings("serial")
@Named("UserVM")
@SessionScoped
public class UserViewModel implements Serializable{
	@Email(message="You must enter a valid e-mail")
	private String email;
	@Iban(message="You must enter a valid iban!")
	private String iban;
	@TcKimlikNo(message="You must enter a valid identity no!")
	private String identityNo;
	@Username
	private String username;
	@StrongPassword
	private String password;
	

	public UserViewModel() {
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getIban() {
		return iban;
	}


	public void setIban(String iban) {
		this.iban = iban;
	}


	public String getIdentityNo() {
		return identityNo;
	}


	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
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

	public String doLogon(){
		if (password.equals("Secret_123")){
			return "search" ;			
		}
		return null;
	}

}
