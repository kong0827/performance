package com.example.demo.dao;

import org.springframework.stereotype.Repository;

import com.example.demo.core.BaseRepository;
import com.example.demo.entity.PlanEntity;

@Repository
public interface PlanDao extends BaseRepository<PlanEntity, Integer> {

}
