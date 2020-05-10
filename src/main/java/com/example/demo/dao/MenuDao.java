package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.core.BaseRepository;
import com.example.demo.entity.MenuEntity;

@Repository
public interface MenuDao extends BaseRepository<MenuEntity, Integer> {
	@Query(value = "select t.* from menu t where menu_id <> 0 ", nativeQuery = true)
	public List<MenuEntity> getList();
	
	@Query(value = "select t.* from menu t where menu_id = 0 ", nativeQuery = true)
	public List<MenuEntity> getPidList();
}
