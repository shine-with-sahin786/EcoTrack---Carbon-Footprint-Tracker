package com.sahin.eco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahin.eco.entity.Notifications;

@Repository
public interface NotificationsDAO extends JpaRepository<Notifications, Long>
{
	List<Notifications> findByUsers4Email(String email);
}
