package com.cfang.WeChat.dao.impl;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hamcrest.core.IsAnything;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
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
		
		return (List<T>) this.getHibernateTemplate().execute(new HibernateCallback<T>() {
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

	@Override
	public List<T> queryParams(String hql, Map<String, Object> map,
			boolean... isUseCache) {
		
		return null;
	}

	@Override
	public List<T> queryParams(Map<String, Object> map, int start, int maxNum,
			boolean... isUseCache) {
		
		return null;
	}

	@Override
	public List<T> queryParams(String hql, Map<String, Object> map, int start,
			int maxNum, boolean... isUseCache) {
		
		return null;
	}

	@Override
	public T queryByParamUnique(String hql, Map<String, Object> map) {
		
		return null;
	}

	@Override
	public List<T> queryBySQL(String sql, Map<String, Object> params) {
		
		return null;
	}

	@Override
	public List<T> queryBySQL(String sql, Map<String, Object> params,
			Integer limit) {
		
		return null;
	}

	@Override
	public Page<T> findPage(Page<T> page, String hql, Map<String, Object> params) {
		
		return null;
	}

	@Override
	public Page<T> findPageSQL(Page<T> page, String sql,
			Map<String, Object> params) {
		
		return null;
	}

	@Override
	public long countHqlResult(String hql, Map<String, Object> values) {
		
		return 0;
	}

	@Override
	public long countSqlResult(String sql, Map<String, Object> values) {
		
		return 0;
	}

	@Override
	public int findAllCount(Class<T> type) {
		
		return 0;
	}

	@Override
	public int batchExcute(String hql, Map<String, Object> map) {
		
		return 0;
	}

	@Override
	public int batchExcuteSql(String sql, Map<String, Object> map) {
		
		return 0;
	}

	@Override
	public Session getHibernaterSession() {
		
		return null;
	}

	@Override
	public boolean executeStoreProcedure(String name, Map<Integer, String> map) {
		
		return false;
	}

	@Override
	public Object executeFunction(String name,
			Map<Integer, ? extends Object> map, Class entityClass) {
		
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
