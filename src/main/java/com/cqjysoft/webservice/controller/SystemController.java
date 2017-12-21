package com.cqjysoft.webservice.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqjysoft.modules.entity.system.Menu;
import com.cqjysoft.webservice.dto.menusDto;
import com.cqjysoft.webservice.service.SystemService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="/system")
public class SystemController {
	@Autowired
	private SystemService systemService;
	
	/**
	 * 获取树状菜单
	 * @param data
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
    @RequestMapping(value="/listtreemenu",method=RequestMethod.POST)
    @ResponseBody
    public String listtreemenu(String params,String token) throws ParseException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Menu> menus = systemService.getAllMenus();
		map.put("code", "SUCCESS");
		map.put("msg", "获取树状菜单成功");
		map.put("data", menus);
        return mapper.writeValueAsString(map);
    }
    
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/savemenus",method=RequestMethod.POST)
    @ResponseBody
    public String savemenus(String params,String token) throws ParseException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		menusDto dto = mapper.readValue(params, menusDto.class);
		systemService.save(dto.getMenus());
		map.put("code", "SUCCESS");
		map.put("msg", "保存成功");
        return mapper.writeValueAsString(map);
    }
    
    /**
	 * 获取列表菜单
	 * @param data
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
    @RequestMapping(value="/listmenu",method=RequestMethod.POST)
    @ResponseBody
    public String listrole(String params,String token) throws ParseException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Menu> menus = systemService.list();
		map.put("code", "SUCCESS");
		map.put("msg", "获取列表菜单成功");
		map.put("data", menus);
        return mapper.writeValueAsString(map);
    }
}
