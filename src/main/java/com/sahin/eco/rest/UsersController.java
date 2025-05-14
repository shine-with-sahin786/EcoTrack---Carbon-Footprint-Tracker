package com.sahin.eco.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahin.eco.dto.UsersDTO;
import com.sahin.eco.service.UsersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@Validated
public class UsersController
{
	@Autowired
	private UsersService us_service;
	@PostMapping("/create")
	public ResponseEntity <String> creating(@Valid @RequestBody UsersDTO usdto)
	{
		us_service.creating(usdto);
		return ResponseEntity.ok("User has been registered.");
	}
}