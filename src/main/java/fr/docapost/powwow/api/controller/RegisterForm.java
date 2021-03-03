package fr.docapost.powwow.api.controller;

public class RegisterForm {

	private String userName;
	private String password;
	private String rePassword;
	public RegisterForm() {
		super();
	}
	public RegisterForm(String userName, String password, String rePassword) {
		super();
		this.userName = userName;
		this.password = password;
		this.rePassword = rePassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	
	
}
