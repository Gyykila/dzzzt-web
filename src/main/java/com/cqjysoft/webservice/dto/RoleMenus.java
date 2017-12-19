package com.cqjysoft.webservice.dto;

public class RoleMenus {
	private Long roleId;
	private Long [] menusIds;
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long[] getMenusIds() {
		return menusIds;
	}
	public void setMenusIds(Long[] menusIds) {
		this.menusIds = menusIds;
	}
}
