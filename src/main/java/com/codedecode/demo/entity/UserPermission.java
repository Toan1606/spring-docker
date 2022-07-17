package com.codedecode.demo.entity;

import lombok.Getter;

public enum UserPermission {
	POSTING_ADD("posting:add"), POSTING_UPDATE("posting:update"), POSTING_DELETE("posting:delete");
	
	@Getter
	private String permission;
	
	private UserPermission(String permission) {
		this.permission = permission;
	}
	
}
