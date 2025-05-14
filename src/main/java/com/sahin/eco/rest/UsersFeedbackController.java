package com.sahin.eco.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahin.eco.dto.UsersFeedbackDTO;
import com.sahin.eco.service.UsersFeedbackService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@Validated
public class UsersFeedbackController
{
	@Autowired
	private UsersFeedbackService feedserv;
	
	// Give Feedback
	@PostMapping("/give_feedback")
	public ResponseEntity <String> giveFeedback(@Valid @RequestBody UsersFeedbackDTO feeddto, HttpSession ses)
	{
		String email = (String) ses.getAttribute("email"); // passing email for linking validation
		String name = (String) ses.getAttribute("name");
		feedserv.giveFeedback(feeddto,email,name);
		return ResponseEntity.ok("Feedback has been submitted.");
	}
}
