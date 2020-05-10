package com.example.demo.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PerformancesDao;
import com.example.demo.entity.PageData;
import com.example.demo.entity.PerformancesEntity;
import com.example.demo.entity.query.PerformancesQueryEntity;
import com.example.demo.service.PerformancesService;

@Service
public class PerformancesServiceImpl implements PerformancesService {
    @PersistenceContext //注入的是实体管理器,执行持久化操作
            EntityManager entityManager;
    @Autowired
    PerformancesDao performancesDao;

    @Override
    public void savePerformances(PerformancesEntity performancesEntity) {
        performancesDao.save(performancesEntity);
    }

    @Override
    public void deletePerformances(Integer id) {
        performancesDao.delete(performancesDao.findById(id).get());
    }

    @Override
    public PageData list(String name, String dimension) {
        PageData pageData = new PageData();
        String sql = "select p.*,i.name as i_name,i.define,u.name as user_name from performances p LEFT JOIN indicators i on p.indicators_id = i.id LEFT JOIN `user` u on p.user_id = u.id where 1=1";
        if (name.length() > 0) {
            sql += " and u.name like '%" + name + "%'";
        }
        if (dimension.length() > 0) {
            sql += " and p.dimension = '" + dimension + "'";
        }
        Query query = entityManager.createNativeQuery(sql, PerformancesQueryEntity.class);
        pageData.setData(query.getResultList());
        return pageData;
    }

    @Override
    public PerformancesEntity getPerformances(Integer id) {
        return performancesDao.findById(id).get();
    }

    @Override
    public PageData getServiceList(Integer id, Integer taskId) {
        PageData pageData = new PageData();
        String sql = "select p.*,i.name as i_name,i.define,u.name as user_name from performances p LEFT JOIN indicators i on p.indicators_id = i.id LEFT JOIN `user` u on p.user_id = u.id where p.user_id=:userId and p.task_id=:taskId and p.dimension='服务目标'";
        Query query = entityManager.createNativeQuery(sql, PerformancesQueryEntity.class);
        query.setParameter("userId", id);
        query.setParameter("taskId", taskId);
        pageData.setData(query.getResultList());
        return pageData;
    }

    @Override
    public PageData getProfessionalList(Integer id, Integer taskId) {
        PageData pageData = new PageData();
        String sql = "select p.*,i.name as i_name,i.define,u.name as user_name from performances p LEFT JOIN indicators i on p.indicators_id = i.id LEFT JOIN `user` u on p.user_id = u.id where p.user_id=:userId and p.task_id=:taskId and p.dimension='职业化目标'";
        Query query = entityManager.createNativeQuery(sql, PerformancesQueryEntity.class);
        query.setParameter("userId", id);
        query.setParameter("taskId", taskId);
        pageData.setData(query.getResultList());
        return pageData;
    }

    @Override
    public PageData getAbilityList(Integer id, Integer taskId) {
        PageData pageData = new PageData();
        String sql = "select p.*,i.name as i_name,i.define,u.name as user_name from performances p LEFT JOIN indicators i on p.indicators_id = i.id LEFT JOIN `user` u on p.user_id = u.id where p.user_id=:userId and p.task_id=:taskId and p.dimension='能力提升'";
        Query query = entityManager.createNativeQuery(sql, PerformancesQueryEntity.class);
        query.setParameter("userId", id);
        query.setParameter("taskId", taskId);
        pageData.setData(query.getResultList());
        return pageData;
    }

    @Override
    public PageData personalList(String name, String dimension, String userId) {
        PageData pageData = new PageData();
        String sql = "select p.*,i.name as i_name,i.define,u.name as user_name from performances p LEFT JOIN indicators i on p.indicators_id = i.id LEFT JOIN `user` u on p.user_id = u.id where 1=1 ";
        if (name.length() > 0) {
            sql += " and u.name like '%" + name + "%'";
        }
        if (dimension.length() > 0) {
            sql += " and p.dimension = '" + dimension + "'";
        }
        sql += " and p.user_id = " + userId;
        Query query = entityManager.createNativeQuery(sql, PerformancesQueryEntity.class);
        pageData.setData(query.getResultList());
        return pageData;
    }
}
