package com.sahin.eco.dto;

public class UsersFeedbackDTOShowAdmin
{
	private String profileImageUrl;
	private int ratings;
	private String comments;
	private String email;
	private String name;
	private long totFeed;
	
	// Getter and Setter
	public long getTotFeed() {
		return totFeed;
	}
	public void setTotFeed(long totFeed) {
		this.totFeed = totFeed;
	}
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
