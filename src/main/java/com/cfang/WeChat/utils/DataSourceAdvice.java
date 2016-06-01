package com.cfang.WeChat.utils;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

@Deprecated
public class DataSourceAdvice implements MethodBeforeAdvice{

	private static Logger LOG = LoggerFactory.getLogger(DataSourceAdvice.class);
	
	String[] methodStartWiths = new String[]{"add","create","save","edit","update","delete","remove"};
	
	@Override
	public void before(Method method, Object[] arg1, Object target)
			throws Throwable {
		if(StringUtils.startsWithAny(
				method.getName().toLowerCase(), methodStartWiths)){
			DataSourceSwitcher.setMaster(); 
			System.out.println("Class Name[" + target.getClass().getName() + 
					"],method name[" + method.getName() + "] use **Master** DataSource!!!");
		}else{
			System.out.println("Class Name[" + target.getClass().getName() + 
					"],method name[" + method.getName() + "] use **Slave** DataSource!!!");
            DataSourceSwitcher.setSlave(); 
        }  
	}

	
	
}
