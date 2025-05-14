package com.sahin.eco.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahin.eco.dto.SubscriptionShowDTO;
import com.sahin.eco.service.AdminSubscriptionService;

@RestController
@RequestMapping("/admin")
public class AdminSubscriptionController
{
	@Autowired
	private AdminSubscriptionService subserv;
	
	// Showing all active users and count total active subscriptions
	@GetMapping("/active_users")
	public ResponseEntity<List<SubscriptionShowDTO>> showActiveUsers()
	{
		List<SubscriptionShowDTO> activities = subserv.showActiveUsers();
		return ResponseEntity.ok(activities); // Will return 200 with the list of DTOs
	}
	// Sum of all amounts
		@GetMapping("/total-amount")
	    public ResponseEntity<Integer> getTotalSubscriptionAmount()
		{
	        int total = subserv.getTotalSubscriptionAmount();
	        return ResponseEntity.ok(total);
	    }
	// Count of all amounts
	@GetMapping("/total-count")
	public ResponseEntity<Long> getTotalSubscriptionCount()
	{
		long totalCount = subserv.getTotalSubscriptionCount();
	    return ResponseEntity.ok(totalCount);
	}
}
