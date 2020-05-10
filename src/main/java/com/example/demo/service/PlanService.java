package com.example.demo.service;

import com.example.demo.entity.PageData;
import com.example.demo.entity.PlanEntity;

public interface PlanService {
	void savePlan(PlanEntity planEntity);
	
	void deletePlan(Integer id);
	
	PageData list(String name);
	
	PlanEntity getPlan(Integer id);
	
	PageData planList(String name,Integer userId);
}
