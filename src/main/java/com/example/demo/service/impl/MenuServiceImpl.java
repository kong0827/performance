package com.example.demo.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MenuDao;
import com.example.demo.entity.MenuEntity;
import com.example.demo.entity.PageData;
import com.example.demo.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	@PersistenceContext //注入的是实体管理器,执行持久化操作
    EntityManager entityManager;
	@Autowired
	MenuDao menuDao;
	@Override
	public void saveMenu(MenuEntity menuEntity) {
		menuDao.save(menuEntity);
	}
	@Override
	public void deleteMenu(Integer id) {
		menuDao.delete(menuDao.findById(id).get());
	}
	@Override
	public PageData list(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MenuEntity getMenu(Integer id) {
		return menuDao.findById(id).get();
	}
	@Override
	public List<MenuEntity> menuList(String menuId) {
		String sql="select * from menu where id in("+menuId+")";
		Query query = entityManager.createNativeQuery(sql, MenuEntity.class);
		return query.getResultList();
	}
}
