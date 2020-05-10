package com.example.demo.dao;

import org.springframework.stereotype.Repository;

import com.example.demo.core.BaseRepository;
import com.example.demo.entity.ComplaintEntity;

@Repository
public interface ComplaintDao extends BaseRepository<ComplaintEntity, Integer> {

}
