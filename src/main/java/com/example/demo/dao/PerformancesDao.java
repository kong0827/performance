package com.example.demo.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.core.BaseRepository;
import com.example.demo.entity.PerformancesEntity;

@Repository
public interface PerformancesDao extends BaseRepository<PerformancesEntity, Integer> {
	@Transactional
    @Modifying
	@Query(value = "update performances set states = 1 where user_id=:userId and task_id=:taskId ", nativeQuery = true)
	public void update(@Param("userId") Integer userId,@Param("taskId") Integer taskId);
	
	@Transactional
    @Modifying
	@Query(value = "update performances set states = 0 where user_id=:userId and task_id=:taskId ", nativeQuery = true)
	public void updates(@Param("userId") Integer userId,@Param("taskId") Integer taskId);
	
	@Transactional
    @Modifying
	@Query(value = "update performances set is_score = 1 where task_id=:taskId ", nativeQuery = true)
	public void updatesScore(@Param("taskId") Integer taskId);
	
	@Query(value = "select sum(superior_grade) from performances where task_id =:taskId and user_id =:userId  ", nativeQuery = true)
	public double sum(@Param("taskId") Integer taskId,@Param("userId") Integer userId);
}
