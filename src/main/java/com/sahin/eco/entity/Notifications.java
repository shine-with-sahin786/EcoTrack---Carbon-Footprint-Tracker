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
@Table(name="Notifications")
public class Notifications
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="notify_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="message_for") // FK Column Name
	private Users users4;
	
	@NotBlank
	@Column(name="notification")
	private String notification;
	
	@NotNull
	@Column(name="notified_on")
	private LocalDate today = LocalDate.now();
	
	// Getter and Setter
	public LocalDate getToday() {
		return today;
	}

	public void setToday(LocalDate today) {
		this.today = today;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUsers4() {
		return users4;
	}

	public void setUsers4(Users users4) {
		this.users4 = users4;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}
}
