package com.example.demo.service;

import com.example.demo.entity.PageData;
import com.example.demo.entity.RoleEntity;

public interface RoleService {
	void saveRole(RoleEntity roleEntity);
	
	void deleteRole(Integer id);
	
	PageData list(String name);
	
	RoleEntity getRole(Integer id);
}
