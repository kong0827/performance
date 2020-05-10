package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.core.BaseRepository;
import com.example.demo.entity.TaskEntity;

@Repository
public interface TaskDao extends BaseRepository<TaskEntity, Integer> {
	@Query(value = "select t.* from task t where states = 1 ", nativeQuery = true)
	public List<TaskEntity> getList();
}
