package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.ResultUtil;
import com.example.demo.dao.DepartmentDao;
import com.example.demo.entity.DepartmentEntity;
import com.example.demo.entity.ResultVO;
import com.example.demo.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;
	@Autowired
	DepartmentDao departmentDao;
	
	@PostMapping("/save")
	public ResultVO save(DepartmentEntity departmentEntity) {
		try {
			departmentService.saveDepartment(departmentEntity);
			return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/list")
	public ResultVO list(String name) {
		try {
			return ResultUtil.getSuccessResult(departmentService.list(name));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/delete")
	public ResultVO delete(Integer id) {
		try {
			departmentService.deleteDepartment(id);
			return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	@PostMapping("/get")
	public ResultVO get(Integer id) {
		try {
			return ResultUtil.getSuccessResult(departmentService.getDepartment(id));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	@PostMapping("/find")
	public ResultVO find() {
		try {
			return ResultUtil.getSuccessResult(departmentDao.getList());
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
}
