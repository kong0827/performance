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
import com.example.demo.dao.SubmitDao;
import com.example.demo.entity.ComplaintEntity;
import com.example.demo.entity.ResultVO;
import com.example.demo.entity.SubmitEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.ComplaintService;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {
    @Autowired
    ComplaintService complaintService;
    @Autowired
    SubmitDao submitDao;

    @PostMapping("/save")
    public ResultVO save(Integer taskId, String problem, String id) {
        try {
            HttpSession session = getRequest().getSession();
            UserEntity userEntity = (UserEntity) session.getAttribute("userEntitys");
            SubmitEntity sEntity = submitDao.getModel(taskId, userEntity.getId());
            if (sEntity == null) {
                return ResultUtil.getFailResult("不存在任务记录！");
            }
            ComplaintEntity complaintEntity = new ComplaintEntity();
            if (id.length() > 0) {
                complaintEntity.setId(Integer.valueOf(id));
            }
            complaintEntity.setProblem(problem);
            complaintEntity.setStates(0);
            complaintEntity.setSubmitId(sEntity.getId());
            complaintService.saveComplaint(complaintEntity);
            return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
    }

    @PostMapping("/update")
    public ResultVO update(ComplaintEntity complaintEntity) {
        try {
            ComplaintEntity cEntity = complaintService.getComplaint(complaintEntity.getId());
            cEntity.setStates(complaintEntity.getStates());
            complaintService.saveComplaint(cEntity);
            return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
    }

    @PostMapping("/list")
    public ResultVO list(String name) {
        try {
            return ResultUtil.getSuccessResult(complaintService.list(name));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
    }

    @PostMapping("/lists")
    public ResultVO lists(String name) {
        try {
            return ResultUtil.getSuccessResult(complaintService.list(name));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
    }

    @PostMapping("/delete")
    public ResultVO delete(Integer id) {
        try {
            complaintService.deleteComplaint(id);
            return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
    }

    @PostMapping("/get")
    public ResultVO get(Integer id) {
        try {
            return ResultUtil.getSuccessResult(complaintService.getComplaint(id));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
