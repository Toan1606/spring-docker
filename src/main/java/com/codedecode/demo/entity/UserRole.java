package com.codedecode.demo.entity;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

import lombok.Getter;

public enum UserRole {

	RECRUITER(Sets.newHashSet()), CANDIDATE(Sets.newHashSet()), GUEST(Sets.newHashSet());
	
	@Getter
	private Set<UserPermission> permissions;
	
	private UserRole(Set<UserPermission> permissions) {
		this.permissions = permissions;
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthority() {
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
													.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
													.collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permissions;
	}
}
