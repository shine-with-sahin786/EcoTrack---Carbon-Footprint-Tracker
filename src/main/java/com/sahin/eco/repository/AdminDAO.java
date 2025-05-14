package com.sahin.eco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahin.eco.entity.Admin;

@Repository
public interface AdminDAO extends JpaRepository<Admin,String>{}
