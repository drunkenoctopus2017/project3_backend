package com.revature.authentication.service;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="SYSTEM_USER")
public class SystemUser {
	/**
	 * Version 1.0
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	
	@Size(min=2)
	@NotNull
	private String firstName;
	
	@Size(min=2)
	@NotNull
	private String lastName;
	
	//Temporarily min 1 so that we can have fast typing test data.
	@Size(min=1)
	@NotNull
	private String username;
	
	@Size(min=1)
	@NotNull
	private String password;
	
	protected boolean isEnabled;
	
	protected boolean isAccountNonExpired;
	
	protected boolean isAccountNonLocked;
	
	protected boolean isCredentialsNonExpired;
	
	@ManyToOne
	@JoinColumn(name="ROLE_ID")
	private UserRole role;
	
	@JsonIgnore
	@OneToMany(mappedBy="systemUser")
	private Set<BoardUserJoin> boardUserJoins;
	
	public SystemUser() {}
	
	public SystemUser(SystemUser su) {
		this.id = su.getId();
		this.firstName = su.getFirstName();
		this.lastName = su.getLastName();
		this.username = su.getUsername();
		this.password = su.getPassword();
		this.isEnabled = su.isEnabled();
		this.isAccountNonExpired = su.isAccountNonExpired;
		this.isAccountNonLocked = su.isCredentialsNonExpired;
		this.isCredentialsNonExpired = su.isCredentialsNonExpired;
	}
	
	public SystemUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public SystemUser(int id, String username, String password, boolean isEnabled, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.isEnabled = isEnabled;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	public SystemUser(String username, String password, boolean isEnabled, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired) {
		super();
		this.username = username;
		this.password = password;
		this.isEnabled = isEnabled;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}
	
	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
	public Set<BoardUserJoin> getBoardUserJoins() {
		return boardUserJoins;
	}
	
	public void setBoardUserJoins(Set<BoardUserJoin> boardUserJoins) {
		this.boardUserJoins = boardUserJoins;
	}
	
	@Override
	public String toString() {
		return "SystemUser [boardUserJoins=" + boardUserJoins + ", id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", username=" + username + ", password=" + password + ", isEnabled="
				+ isEnabled + ", isAccountNonExpired=" + isAccountNonExpired + ", isAccountNonLocked="
				+ isAccountNonLocked + ", isCredentialsNonExpired=" + isCredentialsNonExpired + ", role=" + role + "]";
	}
	
}