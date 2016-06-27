package com.cfang.WeChat.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MemCache {
	String prefix();
	long expiration() default 1000*60*60*2;//缓存有效期 1000*60*60*2==2小时过期  
	Class cls();
}
