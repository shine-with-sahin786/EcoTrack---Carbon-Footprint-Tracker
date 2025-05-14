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

import com.sahin.eco.dto.AdminDTO;
import com.sahin.eco.service.AdminForgotService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/admin")
@Validated
@CrossOrigin(origins = "*") // Allow front-end API access
public class AdminForgotController
{
	@Autowired
	private AdminForgotService fpserv;
	
	//fp1 page
	@GetMapping("/fp1/{id}")
	public ResponseEntity<String> checkId
	(@PathVariable @NotBlank(message="Id cannot be null") String id, HttpSession session)
	
	{
		AdminDTO addto = fpserv.checkId(id);
		if(addto!=null)
		{
			session.setAttribute("id", addto.getId());
			return ResponseEntity.ok("Verified");
		}
		else
		{
			return ResponseEntity.status(404).body("Not Found");
		}
	}
	//fp2 page
	@PutMapping("/fp2")
	public ResponseEntity<String> updatePassword(@RequestParam @NotBlank(message="Password cannot be null") String password,
			HttpSession session)
	{
		String id= (String) session.getAttribute("id");
		AdminDTO addto = fpserv.updatePassword(id,password);
		if(addto!=null)
		{
			return ResponseEntity.ok("Password Updated");
		}
		else
		{
			return ResponseEntity.status(404).body("Not Found");
		}
	}
}

