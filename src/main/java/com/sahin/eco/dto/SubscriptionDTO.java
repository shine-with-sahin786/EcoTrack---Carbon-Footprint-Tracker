package com.sahin.eco.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SubscriptionDTO
{
	@NotNull(message="Invalid Amount")
	private int amount;
	
	@NotBlank(message="No Payment Method is chosen")
	private String payMethod;
	
	@NotNull(message="Invalid date format")
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

	public LocalDate getExpiry() {
		return expiry;
	}

	public void setExpiry(LocalDate expiry) {
		this.expiry = expiry;
	}
}
