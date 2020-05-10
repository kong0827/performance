package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.PageData;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.query.TotalQueryEntity;

public interface UserService {
	void saveUser(UserEntity userEntity);
	
	void deleteUser(Integer id);
	
	PageData list(String name);
	
	UserEntity getUser(Integer id);
	
	PageData getUsers(Integer id);
	
	List<TotalQueryEntity> getTotal(Integer id);
}
