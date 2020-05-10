package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.core.BaseRepository;
import com.example.demo.entity.IndicatorsEntity;

@Repository
public interface IndicatorsDao extends BaseRepository<IndicatorsEntity, Integer> {
	@Query(value = "select t.* from indicators t ", nativeQuery = true)
	public List<IndicatorsEntity> getList();
}
