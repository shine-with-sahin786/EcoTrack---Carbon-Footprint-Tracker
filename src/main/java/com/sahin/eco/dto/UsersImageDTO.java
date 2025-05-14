package com.sahin.eco.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UsersImageDTO
{
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Image path is required")
    @Size(max = 255, message = "Image path too long")
    @Pattern(
        regexp = ".*\\.(png|jpg|jpeg|gif|webp)$",
        message = "Only image files are allowed (png, jpg, jpeg, gif, webp)"
    )
    private String imageUrl;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
    
}
