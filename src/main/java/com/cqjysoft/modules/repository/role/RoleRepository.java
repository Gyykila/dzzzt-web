package com.cqjysoft.modules.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqjysoft.modules.entity.role.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
}