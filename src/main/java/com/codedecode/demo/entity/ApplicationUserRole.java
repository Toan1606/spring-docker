package com.codedecode.demo.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

import lombok.Getter;

public enum ApplicationUserRole {
	RECRUITER(Sets.newHashSet()), CANDIDATE(Sets.newHashSet()), GUEST(Sets.newHashSet());
	
	@Getter
	private Set<ApplicationUserPermission> permissions;
	
	private ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		Set<SimpleGrantedAuthority> permissions = new HashSet<SimpleGrantedAuthority>();
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permissions;
	}
}
