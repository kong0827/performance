
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.ResultUtil;
import com.example.demo.dao.PerformancesDao;
import com.example.demo.dao.SubmitDao;
import com.example.demo.dao.TaskDao;
import com.example.demo.entity.ResultVO;
import com.example.demo.entity.TaskEntity;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {
	@Autowired
	TaskService taskService;
	@Autowired
	TaskDao taskDao;
	@Autowired
	PerformancesDao performancesDao;
	@Autowired
	SubmitDao submitDao;
	
	@PostMapping("/save")
	public ResultVO save(TaskEntity taskEntity) {
		try {
			taskService.saveTask(taskEntity);
			return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/update")
	public ResultVO update(TaskEntity taskEntity) {
		try {
			TaskEntity task = taskService.getTask(taskEntity.getId());
			task.setIsScore(taskEntity.getIsScore());
			taskService.saveTask(task);
			performancesDao.updatesScore(task.getId());
			submitDao.update(task.getId());
			return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/list")
	public ResultVO list(String name) {
		try {
			return ResultUtil.getSuccessResult(taskService.list(name));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	@PostMapping("/lists")
	public ResultVO lists(String name) {
		try {
			return ResultUtil.getSuccessResult(taskService.lists(name));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/delete")
	public ResultVO delete(Integer id) {
		try {
			taskService.deleteTask(id);
			return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	@PostMapping("/get")
	public ResultVO get(Integer id) {
		try {
			return ResultUtil.getSuccessResult(taskService.getTask(id));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	@PostMapping("/find")
	public ResultVO find() {
		try {
			return ResultUtil.getSuccessResult(taskDao.getList());
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
}
