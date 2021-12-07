package com.group22.clientservice.service;

import com.group22.clientservice.model.ERole;
import com.group22.clientservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleService extends JpaRepository<Role, Integer> {
	Optional<Role> findByRoleName(ERole role);
}
