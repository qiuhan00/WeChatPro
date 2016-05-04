package com.cfang.WeChat.memcache.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cfang.WeChat.memcache.MemCacheService;
import com.whalin.MemCached.MemCachedClient;

@Service(value="memCacheServiceImpl")
public class MemCacheServiceImpl implements MemCacheService {
	
	@Resource(name="memcachedClient")
	private MemCachedClient memCachedClient;

	@Override
	public Object get(String key) {
		return memCachedClient.get(key);
	}

	@Override
	public boolean set(String key, Object value) {
		return memCachedClient.set(key, value);
	}

	@Override
	public boolean cas(String key, String value, long unique) {
		return memCachedClient.cas(key, value, unique);
	}

	@Override
	public boolean delete(String key) {
		return memCachedClient.delete(key);
	}

	@Override
	public boolean flushAll() {
		return memCachedClient.flushAll();
	}

	@Override
	public boolean exists(String key) {
		return memCachedClient.keyExists(key);
	}

	@Override
	public boolean replace(String key, Object value) {
		return memCachedClient.replace(key, value);
	}

	@Override
	public Map<String, Object> getMulti(String[] keys) {
		return memCachedClient.getMulti(keys);
	}

	@Override
	public boolean set(String key, Object value, Date ExpireTime) {
		return memCachedClient.set(key, value, ExpireTime);
	}

}
