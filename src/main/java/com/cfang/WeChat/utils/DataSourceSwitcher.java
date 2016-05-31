package com.cfang.WeChat.utils;

import org.springframework.util.Assert;

public class DataSourceSwitcher {

	private static final ThreadLocal<String> threadlocal = new ThreadLocal<String>();
	
	public static void setDataSource(String dataSource) {  
        Assert.notNull(dataSource, "dataSource cannot be null");  
        threadlocal.set(dataSource);
    }
  
    public static void setMaster(){  
        clearDataSource();  
    }  
      
    public static void setSlave() {  
        setDataSource("slave"); 
    }  
      
    public static String getDataSource() {  
        return threadlocal.get();  
    }  
  
    public static void clearDataSource() {  
    	threadlocal.remove();  
    } 
}
