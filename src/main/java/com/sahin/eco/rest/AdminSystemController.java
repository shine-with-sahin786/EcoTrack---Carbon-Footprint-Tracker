package com.sahin.eco.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahin.eco.dto.UsersDTOShowAdmin;
import com.sahin.eco.service.AdminSystemService;

@RestController
@RequestMapping("/admin")
public class AdminSystemController
{
	@Autowired
	private AdminSystemService sysserv;
	
	// Showing all active users and count total active subscriptions
	@GetMapping("/system-usage")
	public ResponseEntity<List<UsersDTOShowAdmin>> systemUsage()
	{
		List<UsersDTOShowAdmin> activities = sysserv.systemUsage();
		return ResponseEntity.ok(activities); // Will return 200 with the list of DTOs
	}
}
