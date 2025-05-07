package com.ERPMatrix.Application.Model.User;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements Serializable {

	private String[] authorities;
	private String email;
	private String firstName;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private long id;
	private boolean isActice;
	private boolean isNotExpired;
	private boolean isNotLocked;
	private Date joinDate;
	private Date lastLoginDate;
	private Date lastLoginDateDispaly;
	private String lastName;
	private String managername;
	private Boolean online;
	private String password;
	private String posation;
	private String profileImageUrl;
	private String role; // ROLE_USER{read,update,creat} , ROLE_ADMIN{delete}
	private double salary;
	private String userId;
	private String username;

	public User() {
	}

	public User(String[] authorities, String email, String firstName, long id, boolean isActice, boolean isNotExpired,
			boolean isNotLocked, Date joinDate, Date lastLoginDate, Date lastLoginDateDispaly, String lastName,
			String managername, Boolean online, String password, String posation, String profileImageUrl, String role,
			double salary, String userId, String username) {
		super();
		this.authorities = authorities;
		this.email = email;
		this.firstName = firstName;
		this.id = id;
		this.isActice = isActice;
		this.isNotExpired = isNotExpired;
		this.isNotLocked = isNotLocked;
		this.joinDate = joinDate;
		this.lastLoginDate = lastLoginDate;
		this.lastLoginDateDispaly = lastLoginDateDispaly;
		this.lastName = lastName;
		this.managername = managername;
		this.online = online;
		this.password = password;
		this.posation = posation;
		this.profileImageUrl = profileImageUrl;
		this.role = role;
		this.salary = salary;
		this.userId = userId;
		this.username = username;
	}

	public String[] getAuthorities() {
		return authorities;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public long getId() {
		return id;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public Date getLastLoginDateDispaly() {
		return lastLoginDateDispaly;
	}

	public String getLastName() {
		return lastName;
	}

	public String getManagername() {
		return managername;
	}

	public Boolean getOnline() {
		return online;
	}

	public String getPassword() {
		return password;
	}

	public String getPosation() {
		return posation;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public String getRole() {
		return role;
	}

	public double getSalary() {
		return salary;
	}

	public String getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public boolean isActice() {
		return isActice;
	}

	public boolean isNotExpired() {
		return isNotExpired;
	}

	public boolean isNotLocked() {
		return isNotLocked;
	}

	public void setActice(boolean isActice) {
		this.isActice = isActice;
	}

	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public void setLastLoginDateDispaly(Date lastLoginDateDispaly) {
		this.lastLoginDateDispaly = lastLoginDateDispaly;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setManagername(String managername) {
		this.managername = managername;
	}

	public void setNotExpired(boolean isNotExpired) {
		this.isNotExpired = isNotExpired;
	}

	public void setNotLocked(boolean isNotLocked) {
		this.isNotLocked = isNotLocked;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPosation(String posation) {
		this.posation = posation;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
