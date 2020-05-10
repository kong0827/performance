package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.core.BaseRepository;
import com.example.demo.entity.TemplateEntity;

@Repository
public interface TemplateDao extends BaseRepository<TemplateEntity, Integer> {
	@Query(value = "select t.* from template t ", nativeQuery = true)
	public List<TemplateEntity> getList();
}
