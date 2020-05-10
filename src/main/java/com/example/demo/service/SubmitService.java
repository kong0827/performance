package com.example.demo.service;

import com.example.demo.entity.PageData;
import com.example.demo.entity.SubmitEntity;

public interface SubmitService {
	void saveSubmit(SubmitEntity submitEntity);
	
	void deleteSubmit(Integer id);
	
	PageData list(String name);
	
	SubmitEntity getSubmit(Integer id);
	
	PageData lists(String name);
	
	PageData listss(String name);
	
	PageData planList(String name);
}
