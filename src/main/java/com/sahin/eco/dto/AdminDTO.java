package com.sahin.eco.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class AdminDTO
{
	@NotBlank(message="Id cannot be Empty")
	@Column(name="admin_id")
	private String id;
	
	@NotBlank(message="Please fill the password")
	@Column(name="admin_password")
	private String password;
	
	//Getter and Setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
