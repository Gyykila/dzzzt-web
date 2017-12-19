package com.cqjysoft.webservice.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cqjysoft.modules.entity.system.Menu;
import com.cqjysoft.modules.repository.system.MenuRepository;

@Component
public class SystemService {
	@Autowired
	private MenuRepository menuRepository;
	
	public List<Menu> list() {
		return menuRepository.findAll();
	}
	
	public List<Menu> getAllMenus() {
		List<Menu> menus = menuRepository.findByParentIsNull();
		return getALL(menus);
	}

	private List<Menu> getALL(List<Menu> menus) {
		List<Menu> list = new ArrayList<>();
		for (Menu menu : menus) {
			list = menuRepository.findByParent(menu.getId());
			menu.setChildren(getALL(list));
		}
		return menus;
	}

	public void save(List<Menu> menus) {
		for (Menu menu : menus) {
			for (Menu child : menu.getChildren()) {
				menuRepository.save(child);
			}
			menuRepository.save(menu);
		}
	}
	
	public List<Menu> getMenuByIds(Long[] menuIds) {
		List<Menu> menus = new ArrayList<>();
		for (Long id : menuIds) {
			menus.add(menuRepository.findOne(id));
		}
		return menus;
	}
}
