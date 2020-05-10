package com.example.demo.service;

import com.example.demo.entity.PageData;
import com.example.demo.entity.PerformancesEntity;

public interface PerformancesService {
	void savePerformances(PerformancesEntity performancesEntity);
	
	void deletePerformances(Integer id);
	
	PageData list(String name,String dimension);
	
	PageData personalList(String name,String dimension,String userId);
	
	PerformancesEntity getPerformances(Integer id);
	
	PageData getServiceList(Integer id,Integer taskId);
	PageData getProfessionalList(Integer id,Integer taskId);
	PageData getAbilityList(Integer id,Integer taskId);
}
