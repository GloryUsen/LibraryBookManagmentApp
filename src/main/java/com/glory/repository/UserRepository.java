package com.glory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glory.entity.Role;
import com.glory.entity.User;

@Repository

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<Role> findByName(String name);

}
