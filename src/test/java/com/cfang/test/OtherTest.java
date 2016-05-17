package com.cfang.test;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.cfang.WeChat.dto.MenuDto;

public class OtherTest {

	@Test
	public void test1(){
		List<MenuDto> result = new ArrayList<MenuDto>();
		MenuDto dto = new MenuDto();
		dto.setId(1);
		dto.setName("test");
		result.add(dto);
		dto = new MenuDto();
		dto.setId(1);
		dto.setName("test");
		result.add(dto);
		dto = new MenuDto();
		dto.setId(2);
		dto.setName("test");
		result.add(dto);
		System.out.println(JSONArray.fromObject(result));
	}
}
