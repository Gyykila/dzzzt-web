package com.cqjysoft.modules.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqjysoft.modules.entity.role.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}