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
@Table(name="users_feedback")
public class UsersFeedback
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="feedback_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_email") // FK Column Name
	private Users users2;
	
	@NotBlank
	@Column(name="user_name")
	private String name;
	
	@NotNull
	@Column(name="ratings")
	private int ratings;
	
	@NotBlank
	@Column(name="comments")
	private String comments;
	
	@NotNull
	@Column(name="rated_on")
	private LocalDate today = LocalDate.now();
	
	// Getter and Setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRatings() {
		return ratings;
	}

	public Users getUsers2() {
		return users2;
	}

	public void setUsers2(Users users2) {
		this.users2 = users2;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public LocalDate getToday() {
		return today;
	}

	public void setToday(LocalDate today) {
		this.today = today;
	}
}
