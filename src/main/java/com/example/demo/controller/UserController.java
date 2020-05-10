package com.example.demo.controller;

import java.util.ArrayList;
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
import com.example.demo.dao.DepartmentDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.DepartmentEntity;
import com.example.demo.entity.ResultVO;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.query.TotalQueryEntity;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	UserDao userDao;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	DepartmentDao departmentDao;
	
	@PostMapping("/login")
	public ResultVO login(UserEntity userEntity) {
		try {
			HttpSession session = getRequest().getSession();
			UserEntity user = userDao.getLogin(userEntity.getAccount(), userEntity.getPwd());
			if(user == null) {
				return ResultUtil.getFailResult("用户名或密码有误！");
			}
			session.setAttribute("userEntitys", user);
			return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/save")
	public ResultVO save(UserEntity userEntity) {
		try {
			userService.saveUser(userEntity);
			return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/list")
	public ResultVO list(String name) {
		try {
			return ResultUtil.getSuccessResult(userService.list(name));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/totalList")
	public ResultVO totalList() {
		try {
			List<TotalQueryEntity> arrayList = new ArrayList<TotalQueryEntity>();
			List<DepartmentEntity> list = departmentDao.getList();
			for (DepartmentEntity departmentEntity : list) {
				TotalQueryEntity totalQueryEntity = new TotalQueryEntity();
				totalQueryEntity.setName(departmentEntity.getName());
				Integer A=0;
				Integer B=0;
				Integer C=0;
				Integer D=0;
				double number = 0;
				List<TotalQueryEntity> totalList = userService.getTotal(departmentEntity.getId());
				for (TotalQueryEntity totalQueryEntity2 : totalList) {
					if (totalQueryEntity2.getScore().equals("A")) {
						A+=1;
					}else if(totalQueryEntity2.getScore().equals("B")) {
						B+=1;
					}else if(totalQueryEntity2.getScore().equals("C")) {
						C+=1;
					}else if(totalQueryEntity2.getScore().equals("D")) {
						D+=1;
					}
					number+=totalQueryEntity2.getNumbers().doubleValue();
				}
				totalQueryEntity.setLevelA(A.toString());
				totalQueryEntity.setLevelB(B.toString());
				totalQueryEntity.setLevelC(C.toString());
				totalQueryEntity.setLevelD(D.toString());
				totalQueryEntity.setNumber(number);
				arrayList.add(totalQueryEntity);
			}
			return ResultUtil.getSuccessResult(arrayList);
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/delete")
	public ResultVO delete(Integer id) {
		try {
			userService.deleteUser(id);
			return ResultUtil.getSuccessResult();
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	
	@PostMapping("/get")
	public ResultVO get(Integer id) {
		try {
			return ResultUtil.getSuccessResult(userService.getUser(id));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	@PostMapping("/gets")
	public ResultVO gets(Integer id) {
		try {
			return ResultUtil.getSuccessResult(userService.getUsers(id));
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	@PostMapping("/find")
	public ResultVO find(Integer id) {
		try {
			return ResultUtil.getSuccessResult(userDao.getList());
        } catch (Exception ex) {
            String msg = "原因：" + ex.getMessage();
            return ResultUtil.getFailResult(msg);
        }
	}
	@PostMapping("/getLevel")
	public ResultVO getLevel(Integer id) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("names", "");
			map.put("namess", "");
			UserEntity userEntity = userService.getUser(id);
			DepartmentEntity departmentEntity = departmentService.getDepartment(userEntity.getDepartmentId());
			if(departmentEntity.getDepartmentId() != null) {
				UserEntity user = userDao.getUser(departmentEntity.getDepartmentId());
				if(user == null) {
					return ResultUtil.getSuccessResult(map);
				}
				map.put("names", user.getName());
				DepartmentEntity department = departmentService.getDepartment(user.getDepartmentId());
				if(department.getDepartmentId() != null) {
					if(userDao.getUser(department.getDepartmentId()) == null) {
						return ResultUtil.getSuccessResult(map);
					}
					map.put("namess", userDao.getUser(department.getDepartmentId()).getName());
				}
			}
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
