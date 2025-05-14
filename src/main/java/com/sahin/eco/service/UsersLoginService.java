package com.sahin.eco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sahin.eco.dto.UsersDTO;
import com.sahin.eco.dto.UsersLoginDTO;
import com.sahin.eco.entity.Users;
import com.sahin.eco.repository.UsersDAO;


@Service
public class UsersLoginService
{
	@Autowired
	private UsersDAO usdao;
	
	@Autowired
    private BCryptPasswordEncoder myencoder;  // Injecting Password Encoder to check
	
	// Login Service
	public UsersDTO login(String email, UsersLoginDTO uslogdto)
	{
		Users users = usdao.findById(email).orElse(null);
		if(users!=null && myencoder.matches(uslogdto.getPassword(), users.getPassword()))
		{
			// Debugging purpose
			System.out.println("Entered Password: " + uslogdto.getPassword());
	        
	        // Real Code Logic
			UsersDTO ob = new UsersDTO();
			ob.setName(users.getName());
			ob.setEmail(users.getEmail());
			ob.setBirthday(users.getBirthday());
			ob.setAddress(users.getAddress());
			ob.setGender(users.getGender());
			return ob;
		}
		else
		{
			return null;
		}
	}
}