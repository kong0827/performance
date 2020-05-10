package com.example.demo.service;

import com.example.demo.entity.DepartmentEntity;
import com.example.demo.entity.PageData;

public interface DepartmentService {
	void saveDepartment(DepartmentEntity departmentEntity);
	
	void deleteDepartment(Integer id);
	
	PageData list(String name);
	
	DepartmentEntity getDepartment(Integer id);
}
