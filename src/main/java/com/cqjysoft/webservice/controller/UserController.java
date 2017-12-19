package com.cqjysoft.webservice.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqjysoft.common.security.K3PasswordParse;
import com.cqjysoft.modules.entity.role.Role;
import com.cqjysoft.modules.entity.role.User;
import com.cqjysoft.modules.entity.system.Menu;
import com.cqjysoft.modules.repository.role.RoleRepository;
import com.cqjysoft.modules.repository.role.UserRepository;
import com.cqjysoft.modules.repository.system.MenuRepository;
import com.cqjysoft.webservice.dto.RoleMenus;
import com.cqjysoft.webservice.service.SystemService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private SystemService systemService;
	
	/**
	 * 手机端登录验证
	 * @param data
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
    @RequestMapping(value="/login",method=RequestMethod.POST)
    @ResponseBody
    public String login(String params) throws ParseException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(params, User.class);
		Map<String, Object> map = new HashMap<String, Object>();
		if(user!=null) {
			User realUser = userRepository.findByUsername(user.getUsername());
			if(realUser!=null) {
				if(K3PasswordParse.validatePassword(user.getPassword(), realUser.getPassword())){
					map.put("code", "SUCCESS");
					map.put("msg", "用户验证成功");
					String token = UUID.randomUUID().toString();
					realUser.setToken(token);
					userRepository.save(realUser);
					user.setToken(token);
					user.setRoles(null);
					user.setPassword(null);
					user.setId(realUser.getId());
					map.put("data", user);
				}else {
					map.put("code", "ERROR");
		    		map.put("msg", "密码不正确");
				}
			}else {
				map.put("code", "ERROR");
	    		map.put("msg", "用户不存在");
			}
    	}else {
    		map.put("code", "ERROR");
    		map.put("msg", "上传参数不正确");
    	}
        return mapper.writeValueAsString(map);
    }
    
    /**
     * 手机端自动登录验证
     * @param data
     * @return
     * @throws ParseException
     * @throws IOException
     */
    @RequestMapping(value="/autologin",method=RequestMethod.POST)
    @ResponseBody
    public String autologin(String data) throws ParseException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(data, User.class);
		Map<String, Object> map = new HashMap<String, Object>();
		if(user!=null) {
			User realUser = userRepository.findByUsername(user.getUsername());
			if(realUser!=null) {
				if(realUser.getToken().equals(user.getToken())){
					map.put("code", "SUCCESS");
					map.put("msg", "用户验证成功");
					String token = UUID.randomUUID().toString();
					realUser.setToken(token);
					userRepository.save(realUser);
					user.setId(realUser.getId());
					user.setToken(token);
					map.put("data", user);
				}else {
					map.put("code", "ERROR");
		    		map.put("msg", "token验证失败");
				}
			}else {
				map.put("code", "ERROR");
	    		map.put("msg", "用户不存在");
			}
    	}else {
    		map.put("code", "ERROR");
    		map.put("msg", "上传参数不正确");
    	}
        return mapper.writeValueAsString(map);
    }
    
    /**
	 * 获取菜单列表
	 * @param data
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
    @RequestMapping(value="/listrolemenus",method=RequestMethod.POST)
    @ResponseBody
    public String listrolemenus(String params) throws ParseException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		RoleMenus roleMenus = mapper.readValue(params, RoleMenus.class);
		if(roleMenus.getRoleId()!=null) {
			User user = userRepository.getOne(roleMenus.getRoleId());
			if(user!=null) {
				List<Role> roles = user.getRoles();
				List<Long> rolsId = new ArrayList<>();
				for (Role role : roles) {
					for (Menu rolmenu : role.getMenus()) {
						rolsId.add(rolmenu.getId());
					}
				}
				List<Menu> menus = systemService.getAllMenus();
				for (Menu menu : menus) {
					if(rolsId.contains(menu.getId())) {
						menu.setHidden(false);
					}else {
						menu.setHidden(true);
					}
					for (Menu child : menu.getChildren()) {
						if(rolsId.contains(child.getId())) {
							child.setHidden(false);
						}else {
							child.setHidden(true);
						}
					}
				}
				
				map.put("data", menus);
				map.put("code", "SUCCESS");
	    		map.put("msg", "获取用户菜单成功");
			}else {
				map.put("code", "ERROR");
	    		map.put("msg", "没有对应的用户");
			}
    	}else {
    		map.put("code", "ERROR");
    		map.put("msg", "上传参数不正确");
    	}
        return mapper.writeValueAsString(map);
    }
    
    /**
     * 获取用户组
     * @param data
     * @return
     * @throws ParseException
     * @throws IOException
     */
    @RequestMapping(value="/listrole",method=RequestMethod.POST)
    @ResponseBody
    public String listrole(String data) throws ParseException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Role> roles = roleRepository.findAll();
		List<Long> rolsId;
		List<Menu> menus = systemService.getAllMenus();
		for (Role role : roles) {
			rolsId = new ArrayList<>();
			for (Menu rolmenu : role.getMenus()) {
				rolsId.add(rolmenu.getId());
			}
			for (Menu menu : menus) {
				if(rolsId.contains(menu.getId())) {
					menu.setHidden(false);
				}else {
					menu.setHidden(true);
				}
				for (Menu child : menu.getChildren()) {
					if(rolsId.contains(child.getId())) {
						child.setHidden(false);
					}else {
						child.setHidden(true);
					}
				}
			}
			role.setMenus(menus);
		}
		
		map.put("code", "SUCCESS");
		map.put("msg", "获取用户组成功");
		map.put("data", roles);
        return mapper.writeValueAsString(map);
    }
    
    /**
	 * 手机端登录验证
	 * @param data
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
    @RequestMapping(value="/saverolemenus",method=RequestMethod.POST)
    @ResponseBody
    public String saverolemenus(String params) throws ParseException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		RoleMenus roleMenus = mapper.readValue(params, RoleMenus.class);
		if(roleMenus.getRoleId()!=null) {
			Role role = roleRepository.findOne(roleMenus.getRoleId());
			if(role!=null) {
				List<Menu> menus = systemService.getMenuByIds(roleMenus.getMenusIds());
				role.setMenus(menus);
				roleRepository.saveAndFlush(role);
				map.put("code", "SUCCESS");
	    		map.put("msg", "保存成功");
			}else {
				map.put("code", "ERROR");
	    		map.put("msg", "没有对应的用户组");
			}
    	}else {
    		map.put("code", "ERROR");
    		map.put("msg", "上传参数不正确");
    	}
        return mapper.writeValueAsString(map);
    }
}
