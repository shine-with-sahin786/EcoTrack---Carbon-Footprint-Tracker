package com.sahin.eco.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public class NotificationsDTO
{
	@NotBlank(message="Notification needed.")
	private String notification;
	
	private LocalDate messageDate;
	
	// Getter and Setter
	public LocalDate getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(LocalDate messageDate) {
		this.messageDate = messageDate;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}
	
}
