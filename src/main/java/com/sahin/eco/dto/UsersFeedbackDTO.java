package com.sahin.eco.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsersFeedbackDTO
{
	@NotNull(message="Ratings can't be null")
	private int ratings;
	
	@NotBlank(message="Share us your thoughts")
	private String comments;

	// Getter and Setter
	public int getRatings() {
		return ratings;
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
	
}
