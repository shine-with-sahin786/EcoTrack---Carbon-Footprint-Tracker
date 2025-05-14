package com.sahin.eco.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sahin.eco.dto.UsersDTO;
import com.sahin.eco.service.UsersForgotService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/users")
@Validated
@CrossOrigin(origins = "*") // Allow front-end API access
public class UsersForgotController
{
	@Autowired
	private UsersForgotService fpserv;
	
	//fp1 page
	@GetMapping("/fp1/{email}")
	public ResponseEntity<String> checkEmail
	(@PathVariable @NotBlank(message="Email cannot be null") String email, HttpSession session)
	
	{
		UsersDTO usdto = fpserv.checkEmail(email);
		if(usdto!=null)
		{
			session.setAttribute("security", usdto.getSecurity_qs());
			session.setAttribute("email", usdto.getEmail());
			return ResponseEntity.ok("Verified");
		}
		else
		{
			return ResponseEntity.status(404).body("Not Found");
		}
	}
	// Control All Session
	@GetMapping("/forgot-session")
	public ResponseEntity<String> allSession(HttpSession session)
	{
		String security = (String) session.getAttribute("security");
		if(security!=null)
		{
			return ResponseEntity.ok(security);
		}
		else
		{
			return ResponseEntity.status(404).body("Not Found");
		}
	}
	//fp2 page
	@GetMapping("/fp2")
	public ResponseEntity<String> checkAnswer(@RequestParam String answer, HttpSession session)
	{
		String email = (String) session.getAttribute("email");
		UsersDTO usdto = fpserv.checkAnswer(email,answer);
		if(usdto!=null)
		{
			return ResponseEntity.ok("Matched Answer");
		}
		else
		{
			return ResponseEntity.status(404).body("Not Found");
		}
	}
	//fp3 page
	@PutMapping("/fp3")
	public ResponseEntity<String> updatePassword(@RequestParam @NotBlank(message="Password cannot be null") String password,
			HttpSession session)
	{
		String email = (String) session.getAttribute("email");
		UsersDTO usdto = fpserv.updatePassword(email,password);
		if(usdto!=null)
		{
			return ResponseEntity.ok("Password Updated");
		}
		else
		{
			return ResponseEntity.status(404).body("Not Found");
		}
	}
}
