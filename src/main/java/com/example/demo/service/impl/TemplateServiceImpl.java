package com.example.demo.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TemplateDao;
import com.example.demo.entity.PageData;
import com.example.demo.entity.TemplateEntity;
import com.example.demo.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {
	@PersistenceContext //注入的是实体管理器,执行持久化操作
    EntityManager entityManager;
	@Autowired
	TemplateDao templateDao;
	
	@Override
	public void saveTemplate(TemplateEntity templateEntity) {
		templateDao.save(templateEntity);
	}

	@Override
	public void deleteTemplate(Integer id) {
		templateDao.delete(templateDao.findById(id).get());
	}

	@Override
	public PageData list(String name) {
		return null;
	}

	@Override
	public TemplateEntity getTemplate(Integer id) {
		return templateDao.findById(id).get();
	}

}
