package com.example.demo.service;

import com.example.demo.entity.IndicatorsEntity;
import com.example.demo.entity.PageData;

public interface IndicatorsService {
	void saveIndicators(IndicatorsEntity indicatorsEntity);
	
	void deleteIndicators(Integer id);
	
	PageData list(String name);
	
	IndicatorsEntity getIndicators(Integer id);
}
