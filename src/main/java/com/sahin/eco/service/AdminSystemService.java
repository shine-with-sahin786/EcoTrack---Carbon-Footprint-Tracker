package com.sahin.eco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahin.eco.dto.UsersDTOShowAdmin;
import com.sahin.eco.entity.Users;
import com.sahin.eco.repository.UsersDAO;

@Service
public class AdminSystemService
{
	@Autowired
	private UsersDAO usdao;
	
	// Show SYSTEM USAGE to Admin
	public List<UsersDTOShowAdmin> systemUsage()
	{
		long totUsers = usdao.count();
		List<Users> entityList = usdao.findAll();
		return entityList.stream().map(entity ->{
			UsersDTOShowAdmin dto = new UsersDTOShowAdmin();
			dto.setToday(entity.getToday());
			dto.setBirthday(entity.getBirthday());
			dto.setGender(entity.getGender());
			dto.setTotUsers(totUsers);
			return dto;
		}).toList();
	}
}
