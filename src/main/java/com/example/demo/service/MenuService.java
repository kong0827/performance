package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.MenuEntity;
import com.example.demo.entity.PageData;

public interface MenuService {
	void saveMenu(MenuEntity menuEntity);
	
	void deleteMenu(Integer id);
	
	PageData list(String name);
	
	MenuEntity getMenu(Integer id);
	
	List<MenuEntity> menuList(String menuId);
}
