package com.sahin.eco.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahin.eco.dto.UsersFeedbackDTOShowAdmin;
import com.sahin.eco.service.AdminSupportService;

@RestController
@RequestMapping("/admin")
public class AdminSupportController
{
	@Autowired
	private AdminSupportService subserv;
	
	// Showing all active users and count total active subscriptions
	@GetMapping("/support")
	public ResponseEntity<List<UsersFeedbackDTOShowAdmin>> showActiveUsers()
	{
		List<UsersFeedbackDTOShowAdmin> activities = subserv.support();
		return ResponseEntity.ok(activities); // Will return 200 with the list of DTOs
	}
}
