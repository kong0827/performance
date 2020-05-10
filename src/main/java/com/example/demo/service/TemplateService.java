package com.example.demo.service;

import com.example.demo.entity.PageData;
import com.example.demo.entity.TemplateEntity;

public interface TemplateService {
	void saveTemplate(TemplateEntity templateEntity);
	
	void deleteTemplate(Integer id);
	
	PageData list(String name);
	
	TemplateEntity getTemplate(Integer id);
}
