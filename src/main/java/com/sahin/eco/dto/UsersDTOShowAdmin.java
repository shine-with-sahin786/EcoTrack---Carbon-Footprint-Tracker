package com.sahin.eco.dto;

import java.time.LocalDate;

public class UsersDTOShowAdmin
{
	private long totUsers;
	private String gender;
	private LocalDate birthday;
	private LocalDate today;
	
	// Getter and Setter
	
	public long getTotUsers() {
		return totUsers;
	}
	public LocalDate getToday() {
		return today;
	}
	public void setToday(LocalDate today) {
		this.today = today;
	}
	public void setTotUsers(long totUsers) {
		this.totUsers = totUsers;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
}
