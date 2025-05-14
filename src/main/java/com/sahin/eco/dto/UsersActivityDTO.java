package com.sahin.eco.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsersActivityDTO
{
	@NotBlank(message="Please provide type")
	private String type;
	
	@NotBlank(message="Please fill description")
	private String description;
	
	@NotNull(message="Provide date with us")
	private LocalDate when;
	
	@NotNull(message="Points must be filled")
	private int points;
	
	@NotBlank(message="Please provide location")
	private String location;
	
	private String profileImageUrl;
	
	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String email;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getWhen() {
		return when;
	}

	public void setWhen(LocalDate when) {
		this.when = when;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
