package com.example.demo.service;

import com.example.demo.entity.ComplaintEntity;
import com.example.demo.entity.PageData;

public interface ComplaintService {
	void saveComplaint(ComplaintEntity complaintEntity);
	
	void deleteComplaint(Integer id);
	
	PageData list(String name);
	
	ComplaintEntity getComplaint(Integer id);
	
	PageData lists(String name);
}
