package com.sahin.eco.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahin.eco.dto.SubscriptionDTO;
import com.sahin.eco.dto.SubscriptionShowDTO;
import com.sahin.eco.entity.Subscriptions;
import com.sahin.eco.entity.Users;
import com.sahin.eco.repository.SubscriptionDAO;
import com.sahin.eco.repository.UsersDAO;

import jakarta.transaction.Transactional;

@Service
public class SubscriptionService
{
	@Autowired
	private SubscriptionDAO subdao;
	
	@Autowired
	private UsersDAO usdao;
	
	// Subscription
	@Transactional
	public void subscribe(SubscriptionDTO subdto, String email)
	{
		Subscriptions ob = new Subscriptions();
		ob.setAmount(subdto.getAmount());
		ob.setPayMethod(subdto.getPayMethod());
		ob.setExpiry(subdto.getExpiry());
		
		// Relationship linking with Users
		Users users = usdao.findById(email).orElse(null);
		if (users!=null)
		{
			ob.setUsers3(users);
			subdao.save(ob);
		}
	}
	// Show Subscription Plans
		public List<SubscriptionShowDTO> showPlans(String email)
		{
			LocalDate today = LocalDate.now();
			List<Subscriptions> entityList = subdao.findByUsers3EmailAndExpiryAfter(email, today);
		    
		    return entityList.stream().map(entity -> {
		        SubscriptionShowDTO dto = new SubscriptionShowDTO();
		        dto.setAmount(entity.getAmount());
		        dto.setToday(entity.getToday()); // for accessing started on
		        dto.setExpiry(entity.getExpiry());
		        dto.setPayMethod(entity.getPayMethod());
		        // no recursion here
		        return dto;
		    }).toList();
		}
		 // ✅ Check if user has an active subscription
	    public boolean hasActiveSubscription(String email)
	    {
	        LocalDate today = LocalDate.now();
	        List<Subscriptions> activeSubscriptions = subdao.findByUsers3EmailAndExpiryAfter(email, today);
	        return !activeSubscriptions.isEmpty();
	    }
}
