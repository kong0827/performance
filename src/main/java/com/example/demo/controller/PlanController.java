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
import com.example.demo.entity.PlanEntity;
import com.example.demo.entity.ResultVO;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.PlanService;

@RestController
@RequestMapping("/plan")
public class PlanController {
	@Autowired
	PlanService planService;
	
	@PostMapping("/save")
	public ResultVO save(PlanEntity planEntity) {
		try {
			planService.savePlan(planEntity);
			return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/update")
	public ResultVO update(PlanEntity planEntity) {
		try {
			PlanEntity plan = planService.getPlan(planEntity.getId());
			plan.setStates(1);
			planService.savePlan(plan);
			return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/list")
	public ResultVO list(String name) {
		try {
			return ResultUtil.getSuccessResult(planService.list(name));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	@PostMapping("/lists")
	public ResultVO lists(String name) {
		try {
			HttpSession session = getRequest().getSession();
			UserEntity userEntity = (UserEntity) session.getAttribute("userEntitys");
			return ResultUtil.getSuccessResult(planService.planList(name, userEntity.getId()));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/delete")
	public ResultVO delete(Integer id) {
		try {
			planService.deletePlan(id);
			return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	@PostMapping("/get")
	public ResultVO get(Integer id) {
		try {
			return ResultUtil.getSuccessResult(planService.getPlan(id));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
