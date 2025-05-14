package com.sahin.eco.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahin.eco.dto.SubscriptionShowDTO;
import com.sahin.eco.entity.Subscriptions;
import com.sahin.eco.repository.SubscriptionDAO;

@Service
public class AdminSubscriptionService
{
	@Autowired
	private SubscriptionDAO subdao;
	
	// Show Subscription Plans
	public List<SubscriptionShowDTO> showActiveUsers()
	{
		LocalDate today = LocalDate.now();
		long activated = subdao.countByExpiryAfter(today);
		List<Subscriptions> entityList = subdao.findByExpiryAfter(today);
		return entityList.stream().map(entity ->{
			SubscriptionShowDTO dto = new SubscriptionShowDTO();
			dto.setEmail(entity.getUsers3().getEmail());
			dto.setTotalActive(activated);
			dto.setAmount(entity.getAmount());
			dto.setToday(entity.getToday()); // for accessing started on
			dto.setExpiry(entity.getExpiry());
			dto.setPayMethod(entity.getPayMethod());
			return dto;
		}).toList();
	}
	// Sum of Amount
    public int getTotalSubscriptionAmount()
    {
        Integer total = subdao.sumAllAmounts();
        return total != null ? total : 0; // avoid returning null
    }
    // Count of Amount
    public long getTotalSubscriptionCount()
    {
        return subdao.count(); // built-in method
    }
}
