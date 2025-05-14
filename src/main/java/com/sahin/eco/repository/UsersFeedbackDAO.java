package com.sahin.eco.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sahin.eco.entity.UsersFeedback;

@Repository
public interface UsersFeedbackDAO extends JpaRepository<UsersFeedback, Long>
{}
