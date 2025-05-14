package com.sahin.eco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sahin.eco.dto.AdminDTO;
import com.sahin.eco.entity.Admin;
import com.sahin.eco.repository.AdminDAO;

import jakarta.transaction.Transactional;

@Service
public class AdminService
{
	@Autowired
	private AdminDAO addao;
	
	@Autowired
	private BCryptPasswordEncoder myencoder; // Inject Password Encoder
	
	// Registration Service
	@Transactional
	public void creating(AdminDTO addto)
	{
		Admin ob = new Admin();
		ob.setId(addto.getId());
		ob.setPassword(myencoder.encode(addto.getPassword()));
		addao.save(ob);
	}
	// Login Service
	public AdminDTO login(AdminDTO addto)
	{
		Admin admin = addao.findById(addto.getId()).orElse(null);
		if(admin!=null && myencoder.matches(addto.getPassword(), admin.getPassword()))
		{
			// Debugging purpose
			System.out.println("Entered Password: " + addto.getPassword());
		   // Real Code Logic
			AdminDTO ob = new AdminDTO(); // all fields are null or default
			return ob;
		}
		else
		{
			return null;
		}
	}
}
