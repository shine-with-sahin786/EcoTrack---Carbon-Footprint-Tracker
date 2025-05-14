package com.sahin.eco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahin.eco.entity.UsersImage;

@Repository
public interface UsersImageDAO extends JpaRepository<UsersImage, String>
{
}
