package com.revature.octo.user.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_ROLE")
public class UserRole {
	@Id
	private int id;
	
	private String roleName;
	
	public UserRole() {}
	
	public UserRole(int id, String roleName) {
		this.id = id;
		this.roleName = roleName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", roleName=" + roleName + "]";
	}
}
