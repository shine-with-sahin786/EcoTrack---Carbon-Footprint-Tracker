package com.sahin.eco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sahin.eco.dto.AdminDTO;
import com.sahin.eco.entity.Admin;
import com.sahin.eco.repository.AdminDAO;

@Service
public class AdminForgotService
{
	@Autowired
	private AdminDAO addao;
	
	@Autowired
    private BCryptPasswordEncoder myencoder;  // Injecting Password Encoder to check
	// fp1 page
	public AdminDTO checkId(String id)
	{
		Admin admin = addao.findById(id).orElse(null);
		if(admin!=null)
		{
			AdminDTO ob = new AdminDTO();
			/* Set only id for next page */
			ob.setId(admin.getId());
			return ob;
		}
		else
		{
			return null;
		}
	}
	//fp2 page
	public AdminDTO updatePassword(String id, String password)
	{
		// Received Password
		System.out.println("Accessed : "+password);
		// Finding
		Admin admin = addao.findById(id).orElse(null);
		if(admin!=null)
		{
			// Updating
			admin.setPassword(myencoder.encode(password)); // Encrypt password
			addao.save(admin);
				
			// Setting empty DTO
			AdminDTO ob = new AdminDTO();
			return ob;
		}
		else
		{
			return null;
		}
	}
}

