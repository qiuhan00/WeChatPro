package com.cfang.WeChat.interceptor;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cfang.WeChat.common.MemCache;
import com.cfang.WeChat.common.MemCacheUpdate;
import com.cfang.WeChat.memcache.MemCacheService;
import com.cfang.WeChat.model.User;
import com.cfang.WeChat.utils.Md5Utils;

@Service
@Aspect
public class MemCacheAopImpl implements Ordered{
	
	@Resource(name="memCacheServiceImpl")
	private MemCacheService memCacheService;

	/**
	 * 切入点配置，主要方便同类中其他方法使用此处配置的切入点
	 */
	@Pointcut("execution(* com.cfang.WeChat.service.impl.*.*(..))")
	public void aspect() {

	}
	
	// 配置环绕通知,使用在方法aspect()上注册的切入点
	@Around("aspect()")
	public Object around(JoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object object = null;
//	    object = ((ProceedingJoinPoint) joinPoint).proceed();
		String prefixValue = "1";//默认版本号为1
		ProceedingJoinPoint point = (ProceedingJoinPoint) joinPoint;
		Method[] methods = point.getTarget().getClass().getDeclaredMethods();
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();
		for(Method m : methods){
			if(m.equals(method)){
				if(m.isAnnotationPresent(MemCache.class)){
					MemCache memCache = m.getAnnotation(MemCache.class);
					if(null != memCache){ //如果memcache中存在
						String prefix = memCache.prefix();//获取注解前缀，使用是各个业务包名
						if(null != memCacheService.get(prefix)){//获取版本号
							prefixValue = memCacheService.get(prefix).toString();
						}
						String tempKey = this.getKey(method, point.getArgs(), prefixValue);//获取方法名+参数类型+参数值+版本号 转 MD5
						String key = prefix + "_" + tempKey;//存入memcached的最终key值
						object = memCacheService.get(key);
						if(null == object || object.equals("null")){
							object = point.proceed();//执行aop拦截的方法
							long expiration = memCache.expiration();//获取注解配置memcached死亡时间
                            Date expireTime = new Date(new Date().getTime()+expiration);
                            if(prefixValue.equals("1")){
                            	memCacheService.set(prefix, prefixValue);
                            }
                            memCacheService.set(key, JSON.toJSONString(object), expireTime);
						}else{
							String memresult = object.toString();//如果memcached中存在结果，需要将result反序列化后返回结果
							if(!memresult.startsWith("[")){
								memresult = "["+memresult+"]";
							}
							Type tempType = m.getGenericReturnType();
							List obj = JSON.parseArray(memresult, memCache.cls());
							if(!m.getReturnType().isInterface()){ //返回的为实例对象
								if(!obj.isEmpty() && obj.size() > 0){
									object = obj.get(0);
								}
							}else{  //返回集合对象
								object = obj;
							}
						}
					}
				}else if(m.isAnnotationPresent(MemCacheUpdate.class)){
					MemCacheUpdate memCacheUpdate = m.getAnnotation(MemCacheUpdate.class);
					if(null != memCacheUpdate){
						object = point.proceed();
						String prefix = memCacheUpdate.prefix();
						if(null != memCacheService.get(prefix)){
							prefixValue = memCacheService.get(prefix).toString();
						}
						memCacheService.replace(prefix, Integer.parseInt(prefixValue.toString()) + 1);
						System.out.println(memCacheService.get(prefix).toString());
					}
				}else{
					object = point.proceed();
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("around " + joinPoint + "\tUse time : "
				+ (end - start) + " ms!");
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
		return 2;
	}  
}
