package com.sahin.eco.entity;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="all_users")
public class Users
{
    @NotBlank
    @Column(name="full_name", nullable=false)
	private String name;
    
    @Id
    @NotBlank
    @Email
    @Column(name="email_id", nullable=false, unique=true)
	private String email;
    
    @NotBlank
    @Column(name="password", nullable=false)
	private String password;
    
    @NotBlank
    @Column(name="security", nullable=false)
	private String security_qs;
    
    @NotBlank
    @Column(name="answer", nullable=false)
	private String answer;
    
    @NotEmpty
    @Column(name="address", nullable=false)
	private String address;
    
    @NotNull
    @Column(name="birthday", nullable=false)
    private LocalDate birthday;
    
    @NotBlank
    @Column(name="gender", nullable=false)
	private String gender;
    
    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate today = LocalDate.now();
    
    // Relationship with UsersImage Entity
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "email") // FK column
    private UsersImage us_img;
    
    // Relationship with UsersActivity Entity
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsersActivity> activity;
    
    // Relationship with UsersFeedback Entity
    @OneToMany(mappedBy="users2", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsersFeedback> usfeed;
    
    // Relationship with Subscription Entity
    @OneToMany(mappedBy="users3", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subscriptions> subs;
    
    // Relationship with Notifications Entity
    @OneToMany(mappedBy="users4", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notifications> notify;
    
    // Getter and Setter
    public List<Subscriptions> getSubs() {
		return subs;
	}

	public List<Notifications> getNotify() {
		return notify;
	}

	public void setNotify(List<Notifications> notify) {
		this.notify = notify;
	}

	public void setSubs(List<Subscriptions> subs) {
		this.subs = subs;
	}

	public String getName() {
		return name;
	}

	public List<UsersFeedback> getUsfeed() {
		return usfeed;
	}

	public void setUsfeed(List<UsersFeedback> usfeed) {
		this.usfeed = usfeed;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecurity_qs() {
		return security_qs;
	}

	public void setSecurity_qs(String security_qs) {
		this.security_qs = security_qs;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getToday() {
		return today;
	}

	public void setToday(LocalDate today) {
		this.today = today;
	}

	public UsersImage getUs_img() {
		return us_img;
	}

	public void setUs_img(UsersImage us_img) {
		this.us_img = us_img;
	}

	public List<UsersActivity> getActivity() {
		return activity;
	}

	public void setActivity(List<UsersActivity> activity) {
		this.activity = activity;
	}
	
}
