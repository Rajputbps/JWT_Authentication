package com.jwtAuth.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;

@Entity
public class Role {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    private RoleName name;
	public Role() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public RoleName getName() {
		return name;
	}
	public void setName(RoleName name) {
		this.name = name;
	}
	
	

}
