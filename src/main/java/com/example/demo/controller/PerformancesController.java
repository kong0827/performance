package com.example.demo.controller;

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
import com.example.demo.entity.PerformancesEntity;
import com.example.demo.entity.ResultVO;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.PerformancesService;

@RestController
@RequestMapping("/performances")
public class PerformancesController {
	@Autowired
	PerformancesService performancesService;
	@Autowired
	PerformancesDao performancesDao;
	
	@PostMapping("/save")
	public ResultVO save(PerformancesEntity performancesEntity) {
		try {
			performancesService.savePerformances(performancesEntity);
			return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	@PostMapping("/update")
	public ResultVO update(PerformancesEntity performancesEntity) {
		try {
			HttpSession session = getRequest().getSession();
			UserEntity userEntity = (UserEntity) session.getAttribute("userEntitys");
			performancesEntity.setUserId(userEntity.getId());
			performancesEntity.setAudit(0);
			performancesService.savePerformances(performancesEntity);
			return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	@PostMapping("/updateScore")
	public ResultVO updateScore(PerformancesEntity performancesEntity) {
		try {
			PerformancesEntity performances = performancesService.getPerformances(performancesEntity.getId());
			performances.setCompletion(performancesEntity.getCompletion());
			performances.setScore(performancesEntity.getScore());
			performancesService.savePerformances(performances);
			return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	@PostMapping("/check")
	public ResultVO check(Integer id,Integer audit,String remark) {
		try {
			PerformancesEntity performancesEntity = performancesService.getPerformances(id);
			performancesEntity.setAudit(audit);
			performancesEntity.setNote(remark);
			performancesService.savePerformances(performancesEntity);
			return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	@PostMapping("/list")
	public ResultVO list(String name,String dimension) {
		try {
			return ResultUtil.getSuccessResult(performancesService.list(name,dimension));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	@PostMapping("/personalList")
	public ResultVO personalList(String name,String dimension) {
		try {
			HttpSession session = getRequest().getSession();
			UserEntity userEntity = (UserEntity) session.getAttribute("userEntitys");
			return ResultUtil.getSuccessResult(performancesService.personalList(name,dimension,userEntity.getId().toString()));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/delete")
	public ResultVO delete(Integer id) {
		try {
			performancesService.deletePerformances(id);
			return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/get")
	public ResultVO get(Integer id) {
		try {
			return ResultUtil.getSuccessResult(performancesService.getPerformances(id));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/getServiceList")
	public ResultVO getServiceList(Integer id,Integer taskId) {
		try {
			return ResultUtil.getSuccessResult(performancesService.getServiceList(id,taskId));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	@PostMapping("/getProfessionalList")
	public ResultVO getProfessionalList(Integer id,Integer taskId) {
		try {
			return ResultUtil.getSuccessResult(performancesService.getProfessionalList(id,taskId));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	@PostMapping("/getAbilityList")
	public ResultVO getAbilityList(Integer id,Integer taskId) {
		try {
			return ResultUtil.getSuccessResult(performancesService.getAbilityList(id,taskId));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
