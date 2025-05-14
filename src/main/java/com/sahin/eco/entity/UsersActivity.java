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
@Table(name="users_activity")
public class UsersActivity
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="activity_id")
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "active_user") // FK to Users table
    private Users users;
	
	@NotBlank
	@Column(name="activity_type")
	private String type;
	
	@NotBlank
	@Column(name="description")
	private String description;
	
	@NotNull
	@Column(name="activity_date")
	private LocalDate when;
	
	@NotNull
	@Column(name="points")
	private int points;
	
	@NotBlank
	@Column(name="location")
	private String location;
	
	// Getter and Setter
	public Long getId() {
		return id;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
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
	public void setId(Long id) {
		this.id = id;
	}
	
}
