package com.glory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glory.entity.Role;

@Repository

public interface RoleRepository extends JpaRepository<Role, Long>{

}
