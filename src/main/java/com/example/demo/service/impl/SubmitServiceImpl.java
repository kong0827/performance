package com.example.demo.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.SubmitDao;
import com.example.demo.entity.PageData;
import com.example.demo.entity.SubmitEntity;
import com.example.demo.entity.query.SubmitQueryEntity;
import com.example.demo.service.SubmitService;

@Service
public class SubmitServiceImpl implements SubmitService {
	@PersistenceContext //注入的是实体管理器,执行持久化操作
    EntityManager entityManager;
	@Autowired
	SubmitDao submitDao;
	
	@Override
	public void saveSubmit(SubmitEntity submitEntity) {
		submitDao.save(submitEntity);
	}

	@Override
	public void deleteSubmit(Integer id) {
		submitDao.delete(submitDao.findById(id).get());
	}

	@Override
	public PageData list(String name) {
		PageData pageData = new PageData();
		String sql = "select s.*,u.`name` as userName,t.`name` as taskName,t.is_score as taskScore from submit s LEFT JOIN task t on s.task_id = t.id LEFT JOIN `user` u on s.user_id = u.id where s.states <> 2 ";
		if(name.length() > 0) {
			sql+=" and u.`name` like '%"+name+"%'";
		}
		Query query = entityManager.createNativeQuery(sql, SubmitQueryEntity.class);
		pageData.setData(query.getResultList());
		return pageData;
	}

	@Override
	public SubmitEntity getSubmit(Integer id) {
		return submitDao.findById(id).get();
	}

	@Override
	public PageData lists(String name) {
		PageData pageData = new PageData();
		String sql = "select s.*,u.`name` as userName,t.`name` as taskName,t.is_score as taskScore from submit s LEFT JOIN task t on s.task_id = t.id LEFT JOIN `user` u on s.user_id = u.id where s.is_state = 1 and s.states = 2 ";
		if(name.length() > 0) {
			sql+=" and u.`name` like '%"+name+"%'";
		}
		Query query = entityManager.createNativeQuery(sql, SubmitQueryEntity.class);
		pageData.setData(query.getResultList());
		return pageData;
	}

	@Override
	public PageData listss(String name) {
		PageData pageData = new PageData();
		String sql = "select s.*,u.`name` as userName,t.`name` as taskName,t.is_score as taskScore from submit s LEFT JOIN task t on s.task_id = t.id LEFT JOIN `user` u on s.user_id = u.id where s.is_state = 2 and s.states = 2 ";
		if(name.length() > 0) {
			sql+=" and u.`name` like '%"+name+"%'";
		}
		Query query = entityManager.createNativeQuery(sql, SubmitQueryEntity.class);
		pageData.setData(query.getResultList());
		return pageData;
	}

	@Override
	public PageData planList(String name) {
		PageData pageData = new PageData();
		String sql = "select s.*,u.`name` as userName,t.`name` as taskName,t.is_score as taskScore from submit s LEFT JOIN task t on s.task_id = t.id LEFT JOIN `user` u on s.user_id = u.id where s.is_state = 2 and s.states = 2 and (s.score ='C' or s.score = 'D') ";
		if(name.length() > 0) {
			sql+=" and u.`name` like '%"+name+"%'";
		}
		Query query = entityManager.createNativeQuery(sql, SubmitQueryEntity.class);
		pageData.setData(query.getResultList());
		return pageData;
	}

}
