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
import com.cqjysoft.modules.repository.item.ItemRepository;
import com.cqjysoft.webservice.dto.menusDto;
import com.cqjysoft.webservice.service.SystemService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="/item")
public class ItemController {
    
	@Autowired
	private ItemRepository itemRepository;
    /**
	 * 获取物料列表
	 * @param data
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    @ResponseBody
    public String list(String params,String token) throws ParseException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "SUCCESS");
		map.put("msg", "物料列表菜单成功");
		map.put("data", null);
        return mapper.writeValueAsString(map);
    }
    
    /**
	 * 保存物料列表菜单
	 * @param data
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    @ResponseBody
    public String save(String params,String token) throws ParseException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "SUCCESS");
		map.put("msg", "保存物料成功");
		map.put("data", null);
        return mapper.writeValueAsString(map);
    }
    
    /**
	 * 删除列表菜单
	 * @param data
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public String delete(String params,String token) throws ParseException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "SUCCESS");
		map.put("msg", "删除物料成功");
		map.put("data", null);
        return mapper.writeValueAsString(map);
    }
}
