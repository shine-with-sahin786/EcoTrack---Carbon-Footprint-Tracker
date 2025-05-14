package com.sahin.eco.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="Subscriptions")
public class Subscriptions
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="subscription_id")
	private Long id;
	
	// Relationship
	@ManyToOne
	@JoinColumn(name="subscribed_by")
	private Users users3;
	
	@NotNull
	@Column(name="payment_amount")
	private int amount;
	
	@NotBlank
	@Column(name="payment_method")
	private String payMethod;
	
	@NotNull
	@Column(name="starts_on")
	private LocalDate today = LocalDate.now();
	
	@NotNull
	@Column(name="ends_on")
	private LocalDate expiry;
	
	// Getter and Setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUsers3() {
		return users3;
	}

	public void setUsers3(Users users3) {
		this.users3 = users3;
	}

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
