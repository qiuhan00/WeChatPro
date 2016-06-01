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
		System.out.println("---------------aop before----------");
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
						System.out.println("class:"+point.getTarget().getClass().getSimpleName()
								+",method:"+m.getName()+"-----------  使用master   ----------");
						DataSourceSwitcher.setMaster();
					}else{
						System.out.println("class:"+point.getTarget().getClass().getSimpleName()
								+",method:"+m.getName()+"-----------  使用slave1   ----------");
						DataSourceSwitcher.setSlave();
					}
				}else{
					System.out.println("class:"+point.getTarget().getClass().getSimpleName()
							+",method:"+method.getName()+"-----------  使用slave   ----------");
					DataSourceSwitcher.setSlave();
				}
			}
		}
		System.out.println(joinPoint);
	}
	
	/**
	 * 配置后置通知,使用在方法aspect()上注册的切入点
	 * @param joinPoint
	 */
	@After("aspect()")
	public void after(JoinPoint joinPoint) {
		System.out.println("---------------aop after----------");
		System.out.println(joinPoint);
	}
	
	/**
	 * 配置后置返回通知,使用在方法aspect()上注册的切入点
	 * @param joinPoint
	 */
	@AfterReturning("aspect()")
	public void afterReturn(JoinPoint joinPoint) {
		System.out.println("afterReturn " + joinPoint);
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

	// 配置环绕通知,使用在方法aspect()上注册的切入点
	@Around("aspect()")
	public Object around(JoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		long end = System.currentTimeMillis();
		System.out.println("around " + joinPoint + "\tUse time : "
				+ (end - start) + " ms!");
		Object object = ((ProceedingJoinPoint) joinPoint).proceed();
		return object;
	}
	
	private String getKey(Method method, Object [] args, String prefixValue){  
        StringBuffer sb = new StringBuffer();   
        //获取方法名
        String methodName = method.getName();
        //获取参数类型
        Object[] classTemps = method.getParameterTypes();
        //存入方法名
        sb.append(methodName);
        for (int i = 0; i < args.length; i++) {
            sb.append(classTemps[i]+"&");
            if (null == args[i]) {
                sb.append("null");
            } else if ("".equals(args[i])) {
                sb.append("*");
            } else {
                sb.append(args[i]);
            }
        }
        sb.append(prefixValue);
        return Md5Utils.getMd5(sb.toString());  
    }

	@Override
	public int getOrder() {
		return 1;
	}  
}
