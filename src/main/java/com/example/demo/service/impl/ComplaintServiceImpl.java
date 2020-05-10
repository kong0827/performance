package com.example.demo.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ComplaintDao;
import com.example.demo.entity.ComplaintEntity;
import com.example.demo.entity.PageData;
import com.example.demo.entity.query.ComplaintQueryEntity;
import com.example.demo.service.ComplaintService;

@Service
public class ComplaintServiceImpl implements ComplaintService {
	@PersistenceContext //注入的是实体管理器,执行持久化操作
    EntityManager entityManager;
	@Autowired
	ComplaintDao complaintDao;

	@Override
	public void saveComplaint(ComplaintEntity complaintEntity) {
		complaintDao.save(complaintEntity);
	}

	@Override
	public void deleteComplaint(Integer id) {
		complaintDao.delete(complaintDao.findById(id).get());
	}

	@Override
	public PageData list(String name) {
		PageData pageData = new PageData();
		String sql = "select c.id,u.`name` as userName,t.`name` as taskName,c.problem,c.states,s.task_id,s.user_id from complaint c LEFT JOIN submit s on c.submit_id = s.id LEFT JOIN task t on s.task_id = t.id LEFT JOIN `user` u on s.user_id = u.id where 1=1";
		if(name.length() > 0) {
			sql+=" and u.name like '%"+name+"%'";
		}
		Query query = entityManager.createNativeQuery(sql, ComplaintQueryEntity.class);
		pageData.setData(query.getResultList());
		return pageData;
	}

	@Override
	public ComplaintEntity getComplaint(Integer id) {
		return complaintDao.findById(id).get();
	}

	@Override
	public PageData lists(String name) {
		PageData pageData = new PageData();
		String sql = "select c.id,u.`name` as userName,t.`name` as taskName,c.problem,c.states,s.task_id,s.user_id from complaint c LEFT JOIN submit s on c.submit_id = s.id LEFT JOIN task t on s.task_id = t.id LEFT JOIN `user` u on s.user_id = u.id where c.states=2";
		if(name.length() > 0) {
			sql+=" and u.name like '%"+name+"%'";
		}
		Query query = entityManager.createNativeQuery(sql, ComplaintQueryEntity.class);
		pageData.setData(query.getResultList());
		return pageData;
	}

}
