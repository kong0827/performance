package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.core.BaseRepository;
import com.example.demo.entity.RoleEntity;

@Repository
public interface RoleDao extends BaseRepository<RoleEntity, Integer> {
	@Query(value = "select t.* from role t ", nativeQuery = true)
	public List<RoleEntity> getList();
}
