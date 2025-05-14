package com.sahin.eco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahin.eco.entity.Users;

@Repository
public interface UsersDAO extends JpaRepository<Users, String>
{
}
