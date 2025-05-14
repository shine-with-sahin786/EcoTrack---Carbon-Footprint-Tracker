package com.sahin.eco.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahin.eco.dto.AdminDTO;
import com.sahin.eco.service.AdminService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
@Validated
public class AdminController
{
	@Autowired
	private AdminService adserv;
	
	// Registration API
	@PostMapping("/create")
	public ResponseEntity <String> creating(@Valid @RequestBody AdminDTO addto)
	{
		adserv.creating(addto);
		return ResponseEntity.ok("Admin has been registered.");
	}
	
	// Login API
	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody AdminDTO addto2)
	{
		AdminDTO log = adserv.login(addto2);
		if(log!=null)
		{
			return ResponseEntity.ok("Login Success");
		}
		else
		{
			return ResponseEntity.status(404).body("Not Found");
		}
	}
	//✅ Added Logout API
		@PostMapping("/admin_logout")
		public ResponseEntity<String> logout(HttpSession session) 
		{
			session.invalidate(); // Destroy session
			return ResponseEntity.ok("Logout Success");
		}
}
