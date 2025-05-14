package com.sahin.eco.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users_image")
public class UsersImage
{
    @Id
    @NotBlank
    @Email
    @Column(name="email", nullable=false, unique=true)
	private String email;

    @NotBlank(message = "Image path is required")
    @Size(max = 255, message = "Image path too long")
    @Pattern(
        regexp = ".*\\.(png|jpg|jpeg|gif|webp)$",
        message = "Only image files are allowed (png, jpg, jpeg, gif, webp)"
    )
    @Column(name = "image_url", nullable = false)
    private String imageUrl;
    
    // Relationships
    @OneToOne(mappedBy="us_img")
    private Users users;
    
    // Getter and Setter
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

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
}