package com.cqjysoft.modules.entity.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JV_User")
public class User {
	/**
	 * id
	 */
	@Id
	@Column(name="FID")
	private Long id;
	
	@Column(name="FUsername")
	private String username;
	@Column(name="FPassword")
	private String password;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
