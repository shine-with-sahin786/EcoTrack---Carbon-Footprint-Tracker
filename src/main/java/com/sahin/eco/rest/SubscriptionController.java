package com.sahin.eco.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahin.eco.dto.SubscriptionDTO;
import com.sahin.eco.dto.SubscriptionShowDTO;
import com.sahin.eco.service.SubscriptionService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@Validated
public class SubscriptionController
{
	@Autowired
	private SubscriptionService subserv;
	
	// Subscribe
	@PostMapping("/subscribe")
	public ResponseEntity<String> subscribe(@Valid @RequestBody SubscriptionDTO subdto, HttpSession ses)
	{
		String email = (String) ses.getAttribute("email");
		subserv.subscribe(subdto,email);
		return ResponseEntity.ok("Subscribed Successfully.");
	}
	// Checking Subscribed or not
	@GetMapping("/check_subscription")
	public ResponseEntity<String> checkSubscription(HttpSession ses) {
	    String email = (String) ses.getAttribute("email");
	    if (email == null) {
	        return ResponseEntity.status(401).body("User not logged in.");
	    }
	    
	    boolean hasActiveSubscription = subserv.hasActiveSubscription(email);
	    
	    if (hasActiveSubscription) {
	        return ResponseEntity.ok("You already have an active subscription.");
	    } else {
	        return ResponseEntity.status(400).body("No active subscription found.");
	    }
	}

	// For Showing Subscribed Plans
	@GetMapping("/show_plans")
	public ResponseEntity<List<SubscriptionShowDTO>> showPlans(HttpSession ses)
	{
		String email = (String) ses.getAttribute("email");
		if (email == null)
		{
			return ResponseEntity.badRequest().build(); // Better to return 400 with no body
		}
		    List<SubscriptionShowDTO> activities = subserv.showPlans(email);
		    return ResponseEntity.ok(activities); // Will return 200 with the list of DTOs
		}
}
