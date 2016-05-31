package com.cfang.WeChat.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MasterDataSource {
	String name();
	 
    public static String master = "masterDataSource";
 
    public static String slave = "slaveDataSource";
 
}
