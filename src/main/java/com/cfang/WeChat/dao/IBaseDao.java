package com.cfang.WeChat.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Session;

import com.cfang.WeChat.utils.Page;

/**
 * 基本dao操作
 * 基本持久化接口，主要定义基于泛型的持久化接口，实现基本的增删改查功能
 * @author Alex.Fang
 * @version 2016-4-26 上午10:15:26
 */
public interface IBaseDao<T> {

	void delete(T entity);
	
    T find(Class<T> type, int id);
	
    T find(Class<T> type, int id, LockMode lockMode);
    
    List<T> find(Class<T> type, int start, int maxNum, boolean... isUseCache);
    
    List<T> find(Class<T> type, String ids, boolean... isUseCache);
    
    int save(T entity);
    
    void update(T entiry);
    
    T saveOrUpdate(T entity);
    
    T merge(T entity);
    
    List<T> queryParams(final Class<T> type, final Map<String, Object> map, final boolean... isUseCache);
    
    List<T> queryParams(String hql, Map<String, Object> map, boolean... isUseCache);
    
    List<T> queryParams(Map<String, Object> map, int start, int maxNum, boolean... isUseCache);
    
    List<T> queryParams(String hql, Map<String, Object> map, int start, int maxNum, boolean... isUseCache);
    
    T queryByParamUnique(String hql, Map<String, Object> map);
    
    List<T> queryBySQL(String sql, Map<String, Object> params);
    
    List<T> queryBySQL(String sql, Map<String, Object> params, Integer limit);
    //分页查询
    Page<T> findPage(final Page<T> page, final String hql, final Map<String, Object> params);
    
    Page<T> findPageSQL(final Page<T> page, final String sql, final Map<String, Object> params);
    
    long countHqlResult(final String hql, final Map<String, Object> values);
    
    long countSqlResult(final String sql, final Map<String, Object> values);
    
    int findAllCount(Class<T> type);
    
    int batchExcute(String hql, Map<String, Object> map);
    int batchExcuteSql(String sql, Map<String, Object> map);
    Session getHibernaterSession();
    //执行存储过程
    boolean executeStoreProcedure(String name, Map<Integer, String> map);
    //执行函数
    Object executeFunction(String name, Map<Integer, ? extends Object> map, Class entityClass);
}
