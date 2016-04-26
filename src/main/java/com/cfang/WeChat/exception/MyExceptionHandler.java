package com.cfang.WeChat.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MyExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response,
			Object object, Exception exception) {
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", exception);
		if(exception instanceof ValidateRequestException){ //参数错误
			return new ModelAndView("/error/500", model);
		}
		return null;
	}

}
