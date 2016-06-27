package com.cfang.WeChat.interceptor;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cfang.WeChat.common.MasterDataSource;
import com.cfang.WeChat.utils.DataSourceSwitcher;
import com.cfang.WeChat.utils.Md5Utils;

@Service
@Aspect
public class DataSourceAopImpl implements Ordered{
	
	String[] methodStartWiths = new String[]{"add","create","save","edit","update","delete","remove"};
	/**
	 * 切入点配置，主要方便同类中其他方法使用此处配置的切入点
	 */
	@Pointcut("execution(* com.cfang.WeChat.service.impl.*.*(..))")
	public void aspect() {

	}

	/**
	 * 配置前置通知,使用在方法aspect()上注册的切入点,同时接受JoinPoint切入点对象,可以没有该参数
	 * 
	 * @param joinPoint
	 */
	@Before("aspect()")
	public void before(JoinPoint joinPoint) {
		System.out.println("---------------aop DataSourceSwitcher ----------");
		ProceedingJoinPoint point = (ProceedingJoinPoint) joinPoint;
		Method[] methods = point.getTarget().getClass().getDeclaredMethods();
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();
		String name = null;
		for(Method m : methods){
			if(m.equals(method)){
				MasterDataSource masterDataSource = m.getAnnotation(MasterDataSource.class);
				if(null != masterDataSource){
					name = masterDataSource.name();
					if(null != name && m.isAnnotationPresent(MasterDataSource.class) 
							&& StringUtils.startsWithAny(name.toLowerCase(), methodStartWiths)){
						System.out.println("使用master,"+"class:"+point.getTarget().getClass().getSimpleName()
								+",method:"+m.getName());
						DataSourceSwitcher.setMaster();
					}else{
						System.out.println("使用slave,"+"class:"+point.getTarget().getClass().getSimpleName()
								+",method:"+m.getName());
						DataSourceSwitcher.setSlave();
					}
				}else{
					System.out.println("使用slave,"+"class:"+point.getTarget().getClass().getSimpleName()
							+",method:"+method.getName());
					DataSourceSwitcher.setSlave();
				}
			}
		}
	}
	
	/**
	 * 配置抛出异常后通知,使用在方法aspect()上注册的切入点
	 * @param joinPoint
	 * @param ex
	 */
	@AfterThrowing(pointcut = "aspect()", throwing = "ex")
	public void afterThrow(JoinPoint joinPoint, Exception ex) {
		System.out.println("---------------------异常aop--------------");
		System.out.println("afterThrow " + joinPoint + "\r" + ex.getMessage());
	}
	
	/**
	 * 确保数据源切换在事务开启之前执行,order越小越先执行,其中注解事务在配置文件中配置为order=2
	 */
	@Override
	public int getOrder() {
		return 1;
	} 
	
}
