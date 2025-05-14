package com.sahin.eco.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UsersDTO
{
	@NotBlank(message="Name must be filled out")
	private String name;
    
    @NotBlank(message="Email is must needed")
    @Email(message="Invalid Email Format")
	private String email;
    
    @NotBlank(message="Password is must needed")
	private String password;
    
    @NotBlank(message="Security must be filled out")
	private String security_qs;
    
    @NotBlank(message="Answer must be filled out")
	private String answer;
    
    @NotEmpty(message="Please Provide Address")
	private String address;
    
    @NotNull(message="Birthday cannot be null")
    private LocalDate birthday;
    
    @NotBlank(message="Please provide Gender")
	private String gender;

    // Record Method (stable from JDK 17)
    public record UserSessionResponse(String name, String email, String address, String gender) {}
	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public String getSecurity_qs() {
		return security_qs;
	}

	public void setSecurity_qs(String security_qs) {
		this.security_qs = security_qs;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
