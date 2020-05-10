package com.example.demo.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DepartmentDao;
import com.example.demo.entity.DepartmentEntity;
import com.example.demo.entity.PageData;
import com.example.demo.entity.query.DepartmentQueryEntity;
import com.example.demo.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@PersistenceContext //注入的是实体管理器,执行持久化操作
    EntityManager entityManager;
	@Autowired
	DepartmentDao departmentDao;
	@Override
	public void saveDepartment(DepartmentEntity departmentEntity) {
		departmentDao.save(departmentEntity);
	}
	@Override
	public void deleteDepartment(Integer id) {
		departmentDao.delete(departmentDao.findById(id).get());
	}
	@Override
	public PageData list(String name) {
		PageData pageData = new PageData();
		String sql = "select d.id,d.`name` as name,e.`name` as d_name from department d LEFT JOIN department e on d.department_id = e.id where 1=1";
		if(name.length() > 0) {
			sql+=" and d.name like '%"+name+"%'";
		}
		Query query = entityManager.createNativeQuery(sql, DepartmentQueryEntity.class);
		pageData.setData(query.getResultList());
		return pageData;
	}
	@Override
	public DepartmentEntity getDepartment(Integer id) {
		return departmentDao.findById(id).get();
	}
}
