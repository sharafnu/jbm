package com.innovazions.jbm.common;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Roles {

	ROLE_ADMIN(1), ROLE_USER(2);

	private static final Map<Integer, Roles> lookup = new HashMap<Integer, Roles>();

	static {
		for (Roles role : EnumSet.allOf(Roles.class))
			lookup.put(role.getRoleId(), role);
	}

	Roles(int roleId) {
		this.roleId = roleId;
	}

	private int roleId;

	public int getRoleId() {
		return this.roleId;
	}

	public Roles[] getRoles() {
		return values();
	}

	public static String getRoleName(int roleId) {
		return lookup.get(roleId).toString();
	}
}
