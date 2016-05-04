package com.cfang.WeChat.memcache;

import java.util.Date;
import java.util.Map;

/**
 * memcache服务接口类
 * @author Alex.Fang
 * @version 2016-4-28 上午9:44:05
 * 
 * add:仅当存储空间中不存在键相同的数据时才保存
 * replace:仅当存储空间中存在键相同的数据时才保存
 * set:与add和replace不同，无论何时都保存
 * get:获取数据的方法
 * get_multi:一次取得多条数据；getmulti可以非同步地同时取得多个键值， 其速度要比循环调用get快数十倍
 */
public interface MemCacheService {
	
	Object get(String key);
	
	boolean set(String key, Object value);  
	
	boolean set(String key, Object value, Date ExpireTime);  
	  
    boolean cas(String key, String value, long unique);  
      
    boolean delete(String key);  
    
    boolean exists(String key);
    
    boolean replace(String key, Object value);
    
    Map<String, Object> getMulti(String[] keys);//获取多条缓存数据
  
    boolean flushAll();//清空所有服务器缓存
}
