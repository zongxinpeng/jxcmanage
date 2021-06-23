package com.jxc.jxcmanage.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 角色枚举
 * 2021-06-19 14:21
 */

public enum Role {
	/**
	 * 按照目前的设想菜单分为3大类，权限也分为三类
	 * 
	 */
	SUPERADMIN(0, "超级管理员"), PURCHASE(1, "进销存用户的权限"), RETAILERS(2, "电商类用户的权限"), LOGISTICS(3, "物流类用户的权限");

	private Integer code;
	private String name;

	Role(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Map<Integer, String> getRoleNames(int code) {
		Role[] depRole = Role.values();
		Map<Integer, String> map = new HashMap<>();
		for (Role r : depRole) {
			map.put(r.getCode(), r.getName());
		}
		return map;
	}

}
