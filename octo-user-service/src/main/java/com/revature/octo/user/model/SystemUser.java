package com.revature.octo.user.model;

<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
>>>>>>> 94777b6187120ce6b56a6f59858be1c1a893f6fc
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
=======
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
>>>>>>> 94777b6187120ce6b56a6f59858be1c1a893f6fc
import javax.persistence.Table;
import javax.persistence.Transient;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="SYSTEM_USER")
public class SystemUser {//implements UserDetails {
	/**
	 * Version 1.0
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String username;
	
	private String password;
	
<<<<<<< HEAD
	@Transient
	private int role;
	
=======
>>>>>>> 94777b6187120ce6b56a6f59858be1c1a893f6fc
	private boolean isEnabled;
	
	private boolean isAccountNonExpired;
	
	private boolean isAccountNonLocked;
	
	private boolean isCredentialsNonExpired;
	
<<<<<<< HEAD
	
	/*
	@OneToOne
=======
	@ManyToOne
>>>>>>> 94777b6187120ce6b56a6f59858be1c1a893f6fc
	@JoinColumn(name="ROLE_ID")
	private UserRole role;
	
	@JsonIgnore
	@OneToMany(mappedBy="systemUser")
	private Set<BoardUserJoin> boardUserJoins;
	
	public SystemUser() {}
	
	public SystemUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public SystemUser(int id, String username, String password, int role, boolean isEnabled, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
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

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
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
/*
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("role.getRole()"));
		return authorities;
	}\
	*/
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
<<<<<<< HEAD
		return "SystemUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", role=" + role + ", isEnabled=" + isEnabled + ", isAccountNonExpired="
				+ isAccountNonExpired + ", isAccountNonLocked=" + isAccountNonLocked + ", isCredentialsNonExpired="
				+ isCredentialsNonExpired + "]";
	}

=======
		return "SystemUser [boardUserJoins=" + boardUserJoins + ", id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", username=" + username + ", password=" + password + ", isEnabled="
				+ isEnabled + ", isAccountNonExpired=" + isAccountNonExpired + ", isAccountNonLocked="
				+ isAccountNonLocked + ", isCredentialsNonExpired=" + isCredentialsNonExpired + ", role=" + role + "]";
	}
	
>>>>>>> 94777b6187120ce6b56a6f59858be1c1a893f6fc
}
