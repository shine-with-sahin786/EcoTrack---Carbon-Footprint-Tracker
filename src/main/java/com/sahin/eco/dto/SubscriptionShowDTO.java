package com.sahin.eco.dto;

import java.time.LocalDate;

public class SubscriptionShowDTO
{
	private long totalActive;
	private String email;
	private int amount;
	private String payMethod;
	
	
	public long getTotalActive() {
		return totalActive;
	}
	public void setTotalActive(long totalActive) {
		this.totalActive = totalActive;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private LocalDate today;
	private LocalDate expiry;
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public LocalDate getToday() {
		return today;
	}
	public void setToday(LocalDate today) {
		this.today = today;
	}
	public LocalDate getExpiry() {
		return expiry;
	}
	public void setExpiry(LocalDate expiry) {
		this.expiry = expiry;
	}
	
}
