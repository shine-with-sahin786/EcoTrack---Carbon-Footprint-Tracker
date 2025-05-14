package com.sahin.eco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahin.eco.entity.UsersActivity;

@Repository
public interface UsersActivityDAO extends JpaRepository<UsersActivity,Long>
{
	List<UsersActivity> findByUsersEmail(String email); // Spring Data JPA is smart enough to traverse the relationship: email
}
