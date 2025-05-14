package com.sahin.eco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sahin.eco.dto.UsersDTO;
import com.sahin.eco.entity.Users;
import com.sahin.eco.repository.UsersDAO;


@Service
public class UsersForgotService
{
	@Autowired
	private UsersDAO usdao;
	
	@Autowired
    private BCryptPasswordEncoder myencoder;  // Injecting Password Encoder to check
	// fp1 page
	public UsersDTO checkEmail(String email)
	{
		Users users = usdao.findById(email).orElse(null);
		if(users!=null)
		{
			UsersDTO ob = new UsersDTO();
			/* Set only email and security for next page */
			ob.setEmail(users.getEmail());
			ob.setSecurity_qs(users.getSecurity_qs());
			return ob;
		}
		else
		{
			return null;
		}
	}
	//fp2 page
	public UsersDTO checkAnswer(String email, String answer)
	{
		Users users = usdao.findById(email).orElse(null);
		if(users!=null && myencoder.matches(answer, users.getAnswer()))
		{	
			UsersDTO ob = new UsersDTO();
			ob.setEmail(users.getEmail());
			return ob;
		}
		else
		{
			return null;
		}
	}
	//fp3 page
		public UsersDTO updatePassword(String email, String password)
		{
			// Received Password
			System.out.println("Accessed : "+password);
			// Finding student
			Users users = usdao.findById(email).orElse(null);
			if(users!=null)
			{
				// Updating
				users.setPassword(myencoder.encode(password)); // Encrypt password
				usdao.save(users);
				
				// Setting new DTO
				UsersDTO ob = new UsersDTO();
				ob.setEmail(users.getEmail());
				return ob;
			}
			else
			{
				return null;
			}
		}
}
