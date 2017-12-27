package com.cqjysoft.modules.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqjysoft.modules.entity.item.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
	
}