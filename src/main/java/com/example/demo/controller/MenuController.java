package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.example.demo.dao.MenuDao;
import com.example.demo.entity.MenuEntity;
import com.example.demo.entity.ResultVO;
import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.MenuService;
import com.example.demo.service.RoleService;

@RestController
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	MenuService menuService;
	@Autowired
	MenuDao menuDao;
	@Autowired
	RoleService roleService;
	
	@PostMapping("/list")
	public ResultVO list() {
		try {
			return ResultUtil.getSuccessResult(menuDao.getList());
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/menuList")
	public ResultVO menuList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			HttpSession session = getRequest().getSession();
			UserEntity userEntity = (UserEntity) session.getAttribute("userEntitys");
			RoleEntity roleEntity = roleService.getRole(userEntity.getRoleId());
			List<MenuEntity> menuEntities = menuService.menuList(roleEntity.getPermissions());
			map.put("menuEntities", menuEntities);
			List<MenuEntity> menuLists = menuDao.getPidList();
			map.put("menuLists", menuLists);
			return ResultUtil.getSuccessResult(map);
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
