package com.cfang.test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cfang.WeChat.dao.IBaseDao;
import com.cfang.WeChat.model.User;
import com.cfang.WeChat.service.UserService;
import com.cfang.WeChat.utils.Page;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
@Transactional
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
public class UserTest {
	
	private static Logger logger = Logger.getLogger(UserTest.class);  
	
	@Resource(name="userServiceImpl")
	private UserService userService;
	@Resource(name="baseDaoImpl")
	private IBaseDao baseDaoImpl;
	
	@SuppressWarnings("unchecked")
	@Test
	public void get(){
		User user = this.userService.getUser(1);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
//		map.put("id", 2);
		map.put("passWord", "123321");
		map.put("openId", "o_sadfsdfe");
//		List<User> list = this.baseDaoImpl.queryParams("from User where id=:id", map,0,1, false);
//		logger.info("number:"+list.size()+",提取姓名："+list.get(0).getUserName());
//		user = (User) this.baseDaoImpl.queryByParamUnique("from User where id=:id", map);
//		logger.info("提取的姓名："+user.getUserName());
		Page page = new Page();
		page.setPageNo(2);//起始页，默认1开始
        page.setPageSize(1);
//        this.baseDaoImpl.findPageSQL(page, "select * from t_user where openId=:openid", map);
//        user = (User) page.getResult().get(0);
//        Object[] object = (Object[]) page.getResult().get(0);
//        logger.info("totalCount:"+page.getTotalCount()+",number:"+page.getResult().size()+",提取的姓名："+object[1]);
        logger.info(this.baseDaoImpl.batchExcute("update User set passWord=? where openId=?", map));
//        logger.info(this.baseDaoImpl.batchExcuteSql("update t_user set passWord=:passWord where openId=:openId", map));
        
	}
}
