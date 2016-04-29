package com.cfang.WeChat.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.cfang.WeChat.service.MemCacheService;
import com.whalin.MemCached.MemCachedClient;

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

}
