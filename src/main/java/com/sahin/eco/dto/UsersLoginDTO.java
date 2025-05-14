package com.sahin.eco.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UsersLoginDTO
{
    @NotBlank(message="Email is must needed")
    @Email(message="Invalid Email Format")
	private String email;
    
    @NotBlank(message="Password is must needed")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
