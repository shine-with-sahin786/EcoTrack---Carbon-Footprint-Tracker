package com.sahin.eco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahin.eco.dto.UsersFeedbackDTO;
import com.sahin.eco.entity.Users;
import com.sahin.eco.entity.UsersFeedback;
import com.sahin.eco.repository.UsersDAO;
import com.sahin.eco.repository.UsersFeedbackDAO;

import jakarta.transaction.Transactional;

@Service
public class UsersFeedbackService
{
	@Autowired
	private UsersFeedbackDAO feeddao;
	
	@Autowired
	private UsersDAO usdao;
	
	// Give Feedback
	@Transactional
	public void giveFeedback(UsersFeedbackDTO feeddto, String email, String name)
	{
		UsersFeedback ob = new UsersFeedback();
		ob.setName(name);
		ob.setRatings(feeddto.getRatings());
		ob.setComments(feeddto.getComments());
		
		// Relationship Linking
		Users users = usdao.findById(email).orElse(null);
		if(users!=null)
		{
			ob.setUsers2(users);
			feeddao.save(ob);
		}
	}
}
