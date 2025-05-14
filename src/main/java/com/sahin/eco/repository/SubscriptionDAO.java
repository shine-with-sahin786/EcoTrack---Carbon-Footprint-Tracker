package com.sahin.eco.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sahin.eco.entity.Subscriptions;

@Repository
public interface SubscriptionDAO extends JpaRepository<Subscriptions,Long>
{
	List<Subscriptions> findByUsers3EmailAndExpiryAfter(String email, LocalDate today); // find user's active plans
	
	List<Subscriptions> findByExpiryAfter(LocalDate today); // find all active plans
	
	long countByExpiryAfter(LocalDate today); // count all active plans
	
    @Query("SELECT SUM(s.amount) FROM Subscriptions s") // Basic sum of all amounts
    Integer sumAllAmounts(); // To safely represent potential NULL results
}
