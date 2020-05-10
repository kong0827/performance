package com.example.demo.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.core.BaseRepository;
import com.example.demo.entity.SubmitEntity;

@Repository
public interface SubmitDao extends BaseRepository<SubmitEntity, Integer> {
	@Query(value = "select t.* from submit t where task_id=:taskId and user_id=:userId ", nativeQuery = true)
	public SubmitEntity getModel(@Param("taskId") Integer taskId,@Param("userId") Integer userId);
	
	@Transactional
    @Modifying
	@Query(value = "update submit set states = 0 where task_id=:taskId ", nativeQuery = true)
	public void update(@Param("taskId") Integer taskId);
}
