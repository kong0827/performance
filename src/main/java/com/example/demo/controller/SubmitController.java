package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.core.ResultUtil;
import com.example.demo.dao.PerformancesDao;
import com.example.demo.dao.SubmitDao;
import com.example.demo.entity.ResultVO;
import com.example.demo.entity.SubmitEntity;
import com.example.demo.entity.TaskEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.SubmitService;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/submit")
public class SubmitController {
	@Autowired
	SubmitService submitService;
	@Autowired
	PerformancesDao performancesDao;
	@Autowired
	SubmitDao submitDao;
	@Autowired
	TaskService taskService;
	
	@PostMapping("/save")
	public ResultVO save(SubmitEntity submitEntity) {
		try {
			HttpSession session = getRequest().getSession();
			UserEntity userEntity = (UserEntity) session.getAttribute("userEntitys");
			SubmitEntity sEntity = submitDao.getModel(submitEntity.getTaskId(), userEntity.getId());
			if(sEntity != null) {
				submitService.deleteSubmit(sEntity.getId());
			}
			submitEntity.setUserId(userEntity.getId());
			submitService.saveSubmit(submitEntity);
			performancesDao.update(userEntity.getId(), submitEntity.getTaskId());
			return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/updateNote")
	public ResultVO updateNote(SubmitEntity submitEntity) {
		try {
			SubmitEntity submit = submitService.getSubmit(submitEntity.getId());
			submit.setNote(submitEntity.getNote());
			submit.setStates(submitEntity.getStates());
			submit.setIsState(submitEntity.getIsState());
			TaskEntity task = taskService.getTask(submit.getTaskId());
			if(submit.getStates() == 2 && task.getIsScore() == 1) {
				double sum = performancesDao.sum(submit.getTaskId(), submit.getUserId());
				String score = "";
				if(sum <= 60) {
					score="D";
				}else if(sum > 60 && sum <= 80) {
					score="C";
				}else if(sum > 80 && sum <= 90) {
					score="B";
				}else if(sum > 90 && sum <= 100) {
					score="A";
				}
				submit.setScore(score);
				submit.setNumbers(BigDecimal.valueOf(sum));
			}
			submitService.saveSubmit(submit);
			if(submit.getStates() == 1) {
				performancesDao.updates(submit.getUserId(), submit.getTaskId());
			}
			return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/update")
	public ResultVO update(SubmitEntity submitEntity) {
		try {
			SubmitEntity submit = submitService.getSubmit(submitEntity.getId());
			submit.setIsState(submitEntity.getIsState());
			submit.setStates(submitEntity.getStates());
			submitService.saveSubmit(submit);
			return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/list")
	public ResultVO list(String name) {
		try {
			return ResultUtil.getSuccessResult(submitService.list(name));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	@PostMapping("/lists")
	public ResultVO lists(String name) {
		try {
			return ResultUtil.getSuccessResult(submitService.lists(name));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	@PostMapping("/listss")
	public ResultVO listss(String name) {
		try {
			return ResultUtil.getSuccessResult(submitService.listss(name));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	@PostMapping("/planList")
	public ResultVO planList(String name) {
		try {
			return ResultUtil.getSuccessResult(submitService.planList(name));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/delete")
	public ResultVO delete(Integer id) {
		try {
			submitService.deleteSubmit(id);
			return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	@PostMapping("/get")
	public ResultVO get(Integer id) {
		try {
			return ResultUtil.getSuccessResult(submitService.getSubmit(id));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
