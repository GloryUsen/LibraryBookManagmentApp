package com.glory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glory.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
