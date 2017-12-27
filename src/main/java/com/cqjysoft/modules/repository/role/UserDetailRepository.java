package com.cqjysoft.modules.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqjysoft.modules.entity.role.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
}