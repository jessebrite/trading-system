package com.group22.clientservice.service.impl;

import com.group22.clientservice.model.ERole;
import com.group22.clientservice.model.Role;
import com.group22.clientservice.repository.RoleRepository;
import com.group22.clientservice.service.RoleService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
	private final RoleRepository roleRepository;

	@Override
	public Optional<Role> findByRoleName(ERole role) {
		return roleRepository.findByName(role);
	}
}
