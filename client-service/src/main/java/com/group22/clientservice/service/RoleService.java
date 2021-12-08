package com.group22.clientservice.service;

import com.group22.clientservice.model.ERole;
import com.group22.clientservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleService {
	Optional<Role> findByRoleName(ERole role);
}
