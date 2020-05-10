package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.core.BaseRepository;
import com.example.demo.entity.UserEntity;

@Repository
public interface UserDao extends BaseRepository<UserEntity, Integer> {
	@Query(value = "select t.* from user t ", nativeQuery = true)
	public List<UserEntity> getList();
	
	@Query(value = "select t.* from user t where department_id=:departmentId LIMIT 1 ", nativeQuery = true)
	public UserEntity getUser(@Param("departmentId") Integer departmentId);
	
	@Query(value = "select t.* from user t where account=:account and pwd=:pwd LIMIT 1 ", nativeQuery = true)
	public UserEntity getLogin(@Param("account") String account,@Param("pwd") String pwd);
}

