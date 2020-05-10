package com.example.demo.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PlanDao;
import com.example.demo.entity.PageData;
import com.example.demo.entity.PlanEntity;
import com.example.demo.entity.query.PlanQueryEntity;
import com.example.demo.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService {
	@PersistenceContext //注入的是实体管理器,执行持久化操作
    EntityManager entityManager;
	@Autowired
	PlanDao planDao;

	@Override
	public void savePlan(PlanEntity planEntity) {
		planDao.save(planEntity);
	}

	@Override
	public void deletePlan(Integer id) {
		planDao.delete(planDao.findById(id).get());
	}

	@Override
	public PageData list(String name) {
		PageData pageData = new PageData();
		String sql = "select p.*,t.`name` as taskName,u.`name` as userName,s.score from plan p LEFT JOIN submit s on p.submit_id = s.id LEFT JOIN task t on s.task_id = t.id LEFT JOIN `user` u on s.user_id = u.id where 1=1";
		if(name.length() > 0) {
			sql+=" and u.name like '%"+name+"%'";
		}
		Query query = entityManager.createNativeQuery(sql, PlanQueryEntity.class);
		pageData.setData(query.getResultList());
		return pageData;
	}

	@Override
	public PlanEntity getPlan(Integer id) {
		return planDao.findById(id).get();
	}

	@Override
	public PageData planList(String name,Integer userId) {
		PageData pageData = new PageData();
		String sql = "select p.*,t.`name` as taskName,u.`name` as userName,s.score from plan p LEFT JOIN submit s on p.submit_id = s.id LEFT JOIN task t on s.task_id = t.id LEFT JOIN `user` u on s.user_id = u.id where s.user_id=:userId ";
		if(name.length() > 0) {
			sql+=" and u.name like '%"+name+"%'";
		}
		Query query = entityManager.createNativeQuery(sql, PlanQueryEntity.class);
		query.setParameter("userId", userId);
		pageData.setData(query.getResultList());
		return pageData;
	}

}
