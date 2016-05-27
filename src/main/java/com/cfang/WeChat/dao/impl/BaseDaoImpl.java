package com.cfang.WeChat.dao.impl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.cfang.WeChat.dao.IBaseDao;
import com.cfang.WeChat.utils.Page;

@Repository(value="baseDaoImpl")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	@Resource
	public final void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public T find(Class<T> type, int id) {
		return this.getHibernateTemplate().get(type, id);
	}

	@Override
	public T find(Class<T> type, int id, LockMode lockMode) {
		return this.getHibernateTemplate().get(type, id, lockMode);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(final Class<T> type, final int start, final int maxNum, final boolean... isUseCache) {
//		Criteria criteria = this.getSession().createCriteria(type);
//		criteria.setFirstResult(start);
//		criteria.setMaxResults(maxNum);
//		criteria.setCacheable(isUseCache[0]);
//		return criteria.list();
		return (List<T>) this.getHibernateTemplate().execute(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				Criteria criteria = session.createCriteria(type);
				criteria.setFirstResult(start);
				criteria.setMaxResults(maxNum);
				criteria.setCacheable(isUseCache[0]);
				return (T) criteria.list();
			}
		});
	}

	@Override
	public List<T> find(Class<T> type, String ids, boolean... isUseCache) {
		String[] idArray = ids.split(",");
		Integer[] id = new Integer[idArray.length];
		for(int i = 0, len = idArray.length; i < len; i++){
			id[i] = Integer.parseInt(idArray[i]);
		}
		Criteria criteria = this.getSession().createCriteria(type)
				.add(Restrictions.in("id", id));
		criteria.setCacheable(isUseCache[0]);
		return criteria.list();
	}

	@Override
	public int save(T entity) {
		return (Integer) this.getHibernateTemplate().save(entity);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public T saveOrUpdate(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
		this.getHibernateTemplate().flush();
		return entity;
	}

	@Override
	public T merge(T entity) {
		return this.getHibernateTemplate().merge(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryParams(final Class<T> type, final Map<String, Object> map,final boolean... isUseCache) {
//		Criteria criteria = this.getSession().createCriteria(type);
//		this.createCriteria(map, criteria);
//		criteria.setCacheable(isUseCache[0]);
//		return criteria.list();
		
		return this.getHibernateTemplate().executeFind(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				Criteria criteria = session.createCriteria(type);
				createCriteria(map, criteria);
				criteria.setCacheable(isUseCache[0]);
				return (T) criteria.list();
			}
		});
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryParams(final String hql, final Map<String, Object> map,
			final boolean... isUseCache) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				if(null != map){
					query.setProperties(map);
				}
				query.setCacheable(isUseCache[0]);
				return (T) query.list();
			}
			
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryParams(final String hql,final Map<String, Object> map,final int start,
			final int maxNum, final boolean... isUseCache) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				if(null != map){
					query.setProperties(map);
				}
				query.setFirstResult(start);
				query.setMaxResults(maxNum);
				query.setCacheable(isUseCache[0]);
				return (T)query.list();
			}
			
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public T queryByParamUnique(final String hql, final Map<String, Object> map) {
		return this.getHibernateTemplate().execute(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				if(null != map){
					query.setProperties(map);
				}
				return (T) query.uniqueResult();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryBySQL(final String sql, final Map<String, Object> params) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				if(null != params){
					sqlQuery.setProperties(params);
				}
				return (T)sqlQuery.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryBySQL(final String sql, final Map<String, Object> params,
			final Integer limit) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				if(null != params){
					sqlQuery.setProperties(params);
				}
				if (limit != null && limit > 0) {
					sqlQuery.setMaxResults(limit);
				}
				return (T)sqlQuery.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<T> findPage(Page<T> page, final String hql, final Map<String, Object> params) {
		if(page.isAutoCount()){
			page.setTotalCount(this.countHqlResult(hql, params));
		}
		final int firstResult = page.getFirst() - 1; //起始页
		final int maxResults = page.getPageSize(); //每页最大数据量
		List<T> list = this.getHibernateTemplate().executeFind(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				if(null != params){
					query.setProperties(params);
				}
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResults);
				return (T) query.list();
			}
		});
		page.setResult(list);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page findPageSQL(Page<Object[]> page, final String sql,final Map<String, Object> params) {
		if(page.isAutoCount()){
			page.setTotalCount(this.countSqlResult(sql, params));
		}
		final int firstResult = page.getFirst() - 1; //起始页
		final int maxResults = page.getPageSize(); //每页最大数据量 
		List<Object[]> list = this.getHibernateTemplate().executeFind(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				if(null != params){
					sqlQuery.setProperties(params);
				}
				sqlQuery.setFirstResult(firstResult);
				sqlQuery.setMaxResults(maxResults);
				return (T)sqlQuery.list();
			}
		});
		
		page.setResult(list);
		return page;
	}

	@Override
	public long countHqlResult(String hql, Map<String, Object> values) {
		//hql = hql.toLowerCase();
		hql = "select count(*) from" + StringUtils.substringAfter(hql, "from");
		hql = StringUtils.substringBefore(hql, "order by");
		return (Long)this.queryByParamUnique(hql, values);
	}

	@Override
	public long countSqlResult(String sql, final Map<String, Object> values) {
		sql = sql.toLowerCase();
		sql = "select count(*) from" + StringUtils.substringAfter(sql, "from");
		final String countsql = StringUtils.substringBefore(sql, "order by");
		return (Long)this.getHibernateTemplate().execute(new HibernateCallback<Long>() {
			@Override
			public Long doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery sqlQuery = session.createSQLQuery(countsql);
				if(null != values){
					sqlQuery.setProperties(values);
				}
				BigInteger result = (BigInteger) sqlQuery.uniqueResult();
				Long count = null == result ? 0l : result.longValue();
				return count;
			}
		});
	}

	@Override
	public int findAllCount(Class<T> type) {
		final String countHql = "select count(*) from " + type.getSimpleName();
		return (Integer) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				return session.createQuery(countHql).uniqueResult();
			}
		});
	}

	//eg : update User set passWord=? where openId=?
	@Override
	public int batchExcute(String hql, Map<String, Object> map) {
		return this.getHibernateTemplate().bulkUpdate(hql, map.values().toArray());
	}

	//eg : update t_user set passWord=:passWord where openId=:openId
	@Override
	public int batchExcuteSql(final String sql, final Map<String, Object> map) {
		return (Integer)this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				if(null != map){
					sqlQuery.setProperties(map);
				}
				return sqlQuery.executeUpdate();
			}
		});
	}

	@Override
	public Session getHibernaterSession() {
		return this.getSession();
	}

	@Override
	public boolean executeStoreProcedure(String name, Map<Integer, String> map) {
		return false;
	}

	@Override
	public Object executeFunction(String name,Map<Integer, ? extends Object> map, Class<T> entityClass) {
		
		return null;
	}
	
	private Criteria createCriteria(Map<String, Object> map, Criteria criteria){
		Iterator<String> iterator = map.keySet().iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			criteria.add(Restrictions.eq(key, map.get(key)));
		}
		return criteria;
	}

}
