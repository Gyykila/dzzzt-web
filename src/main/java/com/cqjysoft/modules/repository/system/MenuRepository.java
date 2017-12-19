package com.cqjysoft.modules.repository.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqjysoft.modules.entity.system.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
	List<Menu> findByParentIsNull();
	List<Menu> findByParent(Long parent);
}