package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.core.BaseRepository;
import com.example.demo.entity.DepartmentEntity;

@Repository
public interface DepartmentDao extends BaseRepository<DepartmentEntity, Integer> {
	@Query(value = "select t.* from department t ", nativeQuery = true)
	public List<DepartmentEntity> getList();
}
