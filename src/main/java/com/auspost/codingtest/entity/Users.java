package com.auspost.codingtest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="users")
@XmlRootElement
public class Users {
	@Id
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="ROLE")
	private String role;

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
