package com.sahin.eco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sahin.eco.dto.UsersDTO;
import com.sahin.eco.entity.Users;
import com.sahin.eco.repository.UsersDAO;

import jakarta.transaction.Transactional;

@Service
public class UsersService
{
	@Autowired
	private UsersDAO usdao;
	
	@Autowired
	private BCryptPasswordEncoder myencoder; // Inject Password Encoder

	@Transactional
	public void creating(UsersDTO usdto)
	{
		Users ob = new Users();
		ob.setName(usdto.getName());
		ob.setEmail(usdto.getEmail());
		ob.setPassword(myencoder.encode(usdto.getPassword()));
		ob.setSecurity_qs(usdto.getSecurity_qs());
		ob.setAnswer(myencoder.encode(usdto.getAnswer()));
		ob.setBirthday(usdto.getBirthday());
		ob.setAddress(usdto.getAddress());
		ob.setGender(usdto.getGender());
		// No need to manually set `today`, the entity does it automatically!
		usdao.save(ob);
	}
}
