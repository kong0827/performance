package com.example.demo.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RoleDao;
import com.example.demo.entity.PageData;
import com.example.demo.entity.RoleEntity;
import com.example.demo.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@PersistenceContext //注入的是实体管理器,执行持久化操作
    EntityManager entityManager;
	@Autowired
	RoleDao roleDao;
	@Override
	public void saveRole(RoleEntity roleEntity) {
		roleDao.save(roleEntity);
	}
	@Override
	public void deleteRole(Integer id) {
		roleDao.delete(roleDao.findById(id).get());
	}
	@Override
	public PageData list(String name) {
		PageData pageData = new PageData();
		String sql = "select * from role where 1=1";
		if(name.length() > 0) {
			sql+=" and name like '%"+name+"%'";
		}
		Query query = entityManager.createNativeQuery(sql, RoleEntity.class);
		pageData.setData(query.getResultList());
		return pageData;
	}
	@Override
	public RoleEntity getRole(Integer id) {
		return roleDao.findById(id).get();
	}
}
