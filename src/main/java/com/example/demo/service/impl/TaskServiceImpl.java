package com.example.demo.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TaskDao;
import com.example.demo.entity.PageData;
import com.example.demo.entity.TaskEntity;
import com.example.demo.entity.query.TaskQueryEntity;
import com.example.demo.entity.query.TaskQueryListEntity;
import com.example.demo.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	@PersistenceContext //注入的是实体管理器,执行持久化操作
    EntityManager entityManager;
	@Autowired
	TaskDao taskDao;

	@Override
	public void saveTask(TaskEntity taskEntity) {
		taskDao.save(taskEntity);
	}

	@Override
	public void deleteTask(Integer id) {
		taskDao.delete(taskDao.findById(id).get());
	}

	@Override
	public PageData list(String name) {
		PageData pageData = new PageData();
		String sql = " select t.*,p.`name` as tempName from task t LEFT JOIN template p on t.template_id = p.id where 1=1";
		if(name.length() > 0) {
			sql+=" and t.name like '%"+name+"%'";
		}
		Query query = entityManager.createNativeQuery(sql, TaskQueryEntity.class);
		pageData.setData(query.getResultList());
		return pageData;
	}
	@Override
	public PageData lists(String name) {
		PageData pageData = new PageData();
		String sql = " select t.*,p.`name` as tempName,s.id as submitId,s.states as submitStates,s.note as submitNote from task t LEFT JOIN template p on t.template_id = p.id LEFT JOIN submit s on t.id = s.task_id where t.states=1 ";
		if(name.length() > 0) {
			sql+=" and t.name like '%"+name+"%'";
		}
		Query query = entityManager.createNativeQuery(sql, TaskQueryListEntity.class);
		pageData.setData(query.getResultList());
		return pageData;
	}

	@Override
	public TaskEntity getTask(Integer id) {
		return taskDao.findById(id).get();
	}

}
