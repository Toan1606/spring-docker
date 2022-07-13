package com.codedecode.demo.entity;

import java.util.Set;

import com.google.common.collect.Sets;

import lombok.Getter;

public enum ApplicationUserRole {
	RECRUITER(Sets.newHashSet()), CANDIDATE(Sets.newHashSet()), GUEST(Sets.newHashSet());
	
	@Getter
	private Set<ApplicationUserPermission> permissions;
	
	private ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}
}
