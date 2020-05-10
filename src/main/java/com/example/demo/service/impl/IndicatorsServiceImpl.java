package com.example.demo.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IndicatorsDao;
import com.example.demo.entity.IndicatorsEntity;
import com.example.demo.entity.PageData;
import com.example.demo.service.IndicatorsService;

@Service
public class IndicatorsServiceImpl implements IndicatorsService {
	@PersistenceContext //注入的是实体管理器,执行持久化操作
    EntityManager entityManager;
	@Autowired
	IndicatorsDao indicatorsDao;
	@Override
	public void saveIndicators(IndicatorsEntity indicatorsEntity) {
		indicatorsDao.save(indicatorsEntity);
	}
	@Override
	public void deleteIndicators(Integer id) {
		indicatorsDao.delete(indicatorsDao.findById(id).get());
	}
	@Override
	public PageData list(String name) {
		PageData pageData = new PageData();
		String sql = "select * from indicators where 1=1";
		if(name.length() > 0) {
			sql+=" and name like '%"+name+"%'";
		}
		Query query = entityManager.createNativeQuery(sql, IndicatorsEntity.class);
		pageData.setData(query.getResultList());
		return pageData;
	}
	@Override
	public IndicatorsEntity getIndicators(Integer id) {
		return indicatorsDao.findById(id).get();
	}
}
