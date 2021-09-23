package com.jwtAuth.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Users",uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Users {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long userId;
	private String fname;
	private String lname;
	private String password;
	@Transient
	private String re_password;
	private String phone;
	private String email;
	private int roleId;
	private int addressId;
	private boolean enabled=true;
	 @ManyToMany(fetch = FetchType.LAZY)
	 @JoinTable(name = "userroles",joinColumns = @JoinColumn(name = "userid"),inverseJoinColumns = @JoinColumn(name = "roleid"))
	 private Set<Role> roles = new HashSet<>();

	public Users() {
		// TODO Auto-generated constructor stub
	}

	public Users(String fname, String lname, String password, String phone, String email) {
		 
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.phone = phone;
		this.email = email;
	}

	 
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getRe_password() {
		return re_password;
	}

	public void setRe_password(String re_password) {
		this.re_password = re_password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	
}
