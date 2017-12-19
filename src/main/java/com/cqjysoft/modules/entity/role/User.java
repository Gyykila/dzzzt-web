package com.cqjysoft.modules.entity.role;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="JBOS_User")
public class User {
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="FID")
	private Long id;
	
	@Column(name="FUsername")
	private String username;
	@Column(name="FPassword")
	private String password;
	@Column(name="FToken")
	private String token;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "JBOS_UserRole", joinColumns = { @JoinColumn(name = "FUserID") }, inverseJoinColumns = { @JoinColumn(name = "FRoleID") })
	@NotFound(action = NotFoundAction.IGNORE)
	private List<Role> roles  = new ArrayList<>();
	
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
