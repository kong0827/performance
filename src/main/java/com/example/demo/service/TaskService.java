package com.example.demo.service;

import com.example.demo.entity.PageData;
import com.example.demo.entity.TaskEntity;

public interface TaskService {
	void saveTask(TaskEntity taskEntity);
	
	void deleteTask(Integer id);
	
	PageData list(String name);
	
	TaskEntity getTask(Integer id);
	
	PageData lists(String name);
}
