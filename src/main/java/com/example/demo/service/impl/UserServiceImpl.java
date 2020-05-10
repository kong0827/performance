package com.example.demo.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.PageData;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.query.TotalQueryEntity;
import com.example.demo.entity.query.UserQueryEntity;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@PersistenceContext //注入的是实体管理器,执行持久化操作
    EntityManager entityManager;
	@Autowired
	UserDao userDao;
	@Override
	public void saveUser(UserEntity userEntity) {
		userDao.save(userEntity);
	}
	@Override
	public void deleteUser(Integer id) {
		userDao.delete(userDao.findById(id).get());
	}
	@Override
	public PageData list(String name) {
		PageData pageData = new PageData();
		String sql = "select u.id,u.name,d.name as department_name,r.`name` as role_name,u.account,u.pwd from `user` u LEFT JOIN department d on u.department_id = d.id LEFT JOIN role r on u.role_id = r.id where 1=1 ";
		if(name.length() > 0) {
			sql+=" and u.name like '%"+name+"%'";
		}
		Query query = entityManager.createNativeQuery(sql, UserQueryEntity.class);
		pageData.setData(query.getResultList());
		return pageData;
	}
	@Override
	public UserEntity getUser(Integer id) {
		return userDao.findById(id).get();
	}
	@Override
	public PageData getUsers(Integer id) {
		PageData pageData = new PageData();
		String sql = "select u.id,u.name,d.name as department_name,r.`name` as role_name,u.account,u.pwd from `user` u LEFT JOIN department d on u.department_id = d.id LEFT JOIN role r on u.role_id = r.id where u.id=:id ";
		Query query = entityManager.createNativeQuery(sql, UserQueryEntity.class);
		query.setParameter("id", id);
		pageData.setData(query.getResultList());
		return pageData;
	}
	@Override
	public List<TotalQueryEntity> getTotal(Integer departmentId) {
		String sql = "select u.id,score,numbers,'' as name,'' as number,'' as levelA,'' as levelB,'' as levelC,'' as levelD from `user` u LEFT JOIN submit s on u.id = s.user_id LEFT JOIN task t on s.task_id = t.id where department_id =:departmentId and t.is_score = 1 and s.task_id is not null ";
		Query query = entityManager.createNativeQuery(sql, TotalQueryEntity.class);
		query.setParameter("departmentId", departmentId);
		return query.getResultList();
	}
}
