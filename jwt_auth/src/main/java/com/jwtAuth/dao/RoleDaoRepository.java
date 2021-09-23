package com.jwtAuth.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwtAuth.model.Role;
import com.jwtAuth.model.RoleName;

public interface RoleDaoRepository extends JpaRepository<Role , Long>{

	Optional<Role> findByName(RoleName roleName);
}
