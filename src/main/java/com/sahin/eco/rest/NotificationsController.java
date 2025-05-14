package com.sahin.eco.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sahin.eco.dto.NotificationsDTO;
import com.sahin.eco.service.NotificationsService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("notify")
@Validated
public class NotificationsController
{
	@Autowired
	private NotificationsService noserv;
	
	// Generate Notifications for Users
	@PostMapping("/add")
	public ResponseEntity<String> sendMessage(@Valid @RequestBody NotificationsDTO nodto, @RequestParam String email)
	{
		noserv.sendMessage(nodto,email);
		return ResponseEntity.ok("Message Sent.");
	}
	// Show All Notifications to specific users
	@GetMapping("/view")
	public ResponseEntity<List<NotificationsDTO>> viewMessage(HttpSession ses)
	{
	    String email = (String) ses.getAttribute("email");
	    if (email == null)
	    {
	        return ResponseEntity.badRequest().build(); // Better to return 400 with no body
	    }

	    List<NotificationsDTO> activities = noserv.viewMessage(email);
	    return ResponseEntity.ok(activities); // Will return 200 with the list of DTOs
	}
}
