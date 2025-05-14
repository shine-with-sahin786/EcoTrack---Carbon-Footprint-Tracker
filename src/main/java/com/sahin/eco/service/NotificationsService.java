package com.sahin.eco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahin.eco.dto.NotificationsDTO;
import com.sahin.eco.entity.Notifications;
import com.sahin.eco.entity.Users;
import com.sahin.eco.repository.NotificationsDAO;
import com.sahin.eco.repository.UsersDAO;

import jakarta.transaction.Transactional;

@Service
public class NotificationsService
{
	@Autowired
	private NotificationsDAO nodao;
	
	@Autowired
	private UsersDAO usdao;
	
	// For Adding Activities
	@Transactional
	public void sendMessage(NotificationsDTO nodto, String email)
	{
		Notifications ob = new Notifications();
		ob.setNotification(nodto.getNotification());
		// ✅ Fetch User and link activity
        Users user = usdao.findById(email).orElse(null);
        if (user != null)
        {
            ob.setUsers4(user);
            nodao.save(ob);
        }
	}
	// For Showing Notifications to Users
		public List<NotificationsDTO> viewMessage(String email)
		{
		    List<Notifications> entityList = nodao.findByUsers4Email(email);
		    
		    return entityList.stream().map(entity -> {
		        NotificationsDTO dto = new NotificationsDTO();
		        dto.setNotification(entity.getNotification());
		        dto.setMessageDate(entity.getToday());
		        // no recursion here
		        return dto;
		    }).toList();
		}
}
