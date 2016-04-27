package com.cfang.WeChat.interceptor;

import java.io.Serializable;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

/**
 * hibernate拦截器，处理操作过程中更新公共的字段,eg: createtime、updatetime、操作用户名等
 * 
 * @author Alex.Fang
 * @version 2016-4-27 下午4:54:09
 */
public class HibernateInterceptors extends EmptyInterceptor {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger
			.getLogger(HibernateInterceptors.class);

	private static final String createtime = "createtime";
	private static final String updatetime = "updatetime";

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		for (int i = 0; i < propertyNames.length; i++) {
			if (propertyNames[i].equals(createtime)) { // 找到创建日期并赋值
				if (null == state[i]) {
					state[i] = new Date();
				}
			} else if (propertyNames[i].equals(updatetime)) { // 找到更新日期并赋值
				if (null == state[i]) {
					state[i] = new Date();
				}
			}
		}
		return true;
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		for (int i = 0; i < propertyNames.length; i++) {
			if (propertyNames[i].equals(updatetime)) { // 找到更新日期并更新
				currentState[i] = new Date();
			}
		}
		return true;
	}
}
