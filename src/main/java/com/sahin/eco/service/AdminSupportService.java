package com.sahin.eco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahin.eco.dto.UsersFeedbackDTOShowAdmin;
import com.sahin.eco.entity.UsersFeedback;
import com.sahin.eco.repository.UsersFeedbackDAO;

@Service
public class AdminSupportService
{
	@Autowired
	private UsersFeedbackDAO feeddao;
	
	// Show SYSTEM USAGE to Admin
		public List<UsersFeedbackDTOShowAdmin> support()
		{
			long totFeed = feeddao.count();
			List<UsersFeedback> entityList = feeddao.findAll();
			return entityList.stream().map(entity ->{
				UsersFeedbackDTOShowAdmin dto = new UsersFeedbackDTOShowAdmin();
				dto.setTotFeed(totFeed);
				dto.setComments(entity.getComments());
				dto.setEmail(entity.getUsers2().getEmail());
				dto.setName(entity.getUsers2().getName());
				dto.setRatings(entity.getRatings());
				 // Check for null image
		        if (entity.getUsers2().getUs_img() != null && entity.getUsers2().getUs_img().getImageUrl() != null)
		        {
		            dto.setProfileImageUrl(entity.getUsers2().getUs_img().getImageUrl());
		        }
		        else
		        {
		            dto.setProfileImageUrl("/images/nodp.jpg");
		        }
				return dto;
			}).toList();
		}
}

