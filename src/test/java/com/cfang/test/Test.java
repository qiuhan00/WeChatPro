package com.cfang.test;

import java.util.LinkedHashMap;
import java.util.Map;

public class Test {

	@org.junit.Test
	public void test(){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("openId", "o_sadfsdfe");
		map.put("passWord", "123321");
//		map.put("openId", "o_sadfsdfe");
		System.out.println(map.values().toArray()[0] + "|" +map.values().toArray()[1]);
	}
}
