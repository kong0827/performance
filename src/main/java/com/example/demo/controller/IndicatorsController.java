package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.ResultUtil;
import com.example.demo.dao.IndicatorsDao;
import com.example.demo.entity.IndicatorsEntity;
import com.example.demo.entity.ResultVO;
import com.example.demo.service.IndicatorsService;

@RestController
@RequestMapping("/indicators")
public class IndicatorsController {
	@Autowired
	IndicatorsService indicatorsService;
	@Autowired
	IndicatorsDao indicatorsDao;
	/**
	 * 保存
	 * @param categoryEntity
	 * @return
	 */
	@PostMapping("/save")
	public ResultVO save(IndicatorsEntity indicatorsEntity) {
		try {
			indicatorsService.saveIndicators(indicatorsEntity);
			return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/list")
	public ResultVO list(String name) {
		try {
			return ResultUtil.getSuccessResult(indicatorsService.list(name));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/delete")
	public ResultVO delete(Integer id) {
		try {
			indicatorsService.deleteIndicators(id);
			return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	@PostMapping("/get")
	public ResultVO get(Integer id) {
		try {
			return ResultUtil.getSuccessResult(indicatorsService.getIndicators(id));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/find")
	public ResultVO find() {
		try {
			return ResultUtil.getSuccessResult(indicatorsDao.getList());
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
}
