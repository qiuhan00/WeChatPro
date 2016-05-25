package com.cfang.WeChat.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.cfang.WeChat.dto.MenuDto;
import com.cfang.WeChat.dto.UserDto;
import com.cfang.WeChat.model.OperatorResource;
import com.cfang.WeChat.model.Role;
import com.cfang.WeChat.model.User;
import com.cfang.WeChat.service.UserService;
import com.cfang.WeChat.utils.Constant;
import com.cfang.WeChat.utils.JsonUtils;
import com.cfang.WeChat.utils.Page;

@Controller
@RequestMapping(value="/login")
public class LoginController {

	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return new ModelAndView(Constant.LoginManage.VIEW_LOGIN_PAGE);
	}
	
	@RequestMapping(value="/toLogin")
	public ModelAndView Test(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView("/common/UserIndex");
		try {
			SecurityUtils.getSubject().login(new UsernamePasswordToken(request.getParameter("username"), 
					request.getParameter("password")));
			request.getSession().setAttribute("userName", request.getParameter("username"));
		} catch (AuthenticationException e) {
			view = new ModelAndView(Constant.LoginManage.VIEW_LOGIN_PAGE);
			view.addObject("errorMsg", "用户名或者密码错误"); 
		}
		return view;
	}
	
//	@RequestMapping(value="/logout")
//	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
//		ModelAndView view = new ModelAndView("redirect:/login/index");
//		SecurityUtils.getSubject().logout();
//		return view;
//	}
	
	@RequestMapping("/403")  
    public ModelAndView unauthorizedRole(){  
        return new ModelAndView("/common/403");
    }  
	
	@RequestMapping(value="/loadMenu")
	public ModelAndView loadMenu(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		String s1 = "{id:1, parent_id:0, name:\"test1\",pwd:123,click:false}";  
//        String s2 = "{id:2, parent_id:1, name:\"test2\" ,click:false, open:true}";  
//        String s3 = "{id:3, parent_id:1, name:\"test3\",resourceURL:'https://www.baidu.com'}";  
//        String s4 = "{id:4, parent_id:2, name:\"所有用户\",resourceURL: '/login/user' }";  
//        List<String> lstTree = new ArrayList<String>();  
//        lstTree.add(s1);  
//        lstTree.add(s2);  
//        lstTree.add(s3);  
//        lstTree.add(s4);  
//        JsonUtils.renderJson(response, lstTree);
    	//加载菜单项
        List<Role> list = this.userService.getUser((String)SecurityUtils.getSubject().getPrincipals().iterator().next()).getRoleList();
        List<OperatorResource> resources = getUserResources(list);
        Collections.sort(resources, new Comparator<OperatorResource>() {
			@Override
			public int compare(OperatorResource o1, OperatorResource o2) {
				return o1.getId() - o2.getId();
			}
        	
		});
    	JsonUtils.renderJson(response, new ArrayList(getMenus(resources)));
		return null;
	}
	
	@Resource(name="userServiceImpl")
	private UserService userService; 
	
	@RequestMapping(value="/user")
	public ModelAndView user(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView view = new ModelAndView("/user/Users");
		return view;
	}
	
	@RequestMapping(value="/find")
	public ModelAndView find(HttpServletRequest request, HttpServletResponse response, UserDto userDto) throws Exception{
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(User.class, "userName","openId","createTime");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", "总计");
		params.put("openId", "test footer");
		Page page = new Page(50);
		page.setPageNo(1);
		page.setPageSize(20);
		page.setTotalCount(2);
		page.setResult(this.userService.getUser());
		JsonUtils.renderJson(response, page, params, "", filter);
		return null;
	}
	
	private List<OperatorResource> getUserResources(List<Role> list){
		List<OperatorResource> resources = new ArrayList<OperatorResource>();
		Iterator<Role> iterator = list.iterator();
		while(iterator.hasNext()){
			Role role = iterator.next();
			for(Iterator<OperatorResource> it = role.getResources().iterator();it.hasNext();){  
				resources.add(it.next()); 
	        } 
		}
		return resources;
	}
	
	private List<MenuDto> getMenus(List<OperatorResource> resources){
		List<MenuDto> result = new ArrayList<MenuDto>();
		MenuDto menuDto = null;
		Iterator<OperatorResource> iterator = resources.iterator();
		OperatorResource operatorResource = null;
		List<OperatorResource> childs = null;
		while(iterator.hasNext()){
			operatorResource = iterator.next();
			childs = operatorResource.loadSortedChildren();
			if(null == childs || childs.isEmpty())
				continue;
			//添加父菜单
			result.add(getDto(operatorResource, menuDto));
			//添加二级菜单
			Iterator<OperatorResource> iterator_1 = childs.iterator();
			while(iterator_1.hasNext()){
				operatorResource = iterator_1.next();
				result.add(getDto(operatorResource, menuDto));
			}
		}
		return result;
	}
	
	private MenuDto getDto(OperatorResource operatorResource, MenuDto menuDto){
		menuDto = new MenuDto();
		menuDto.setId(operatorResource.getId());
		menuDto.setName(operatorResource.getResourceName());
		menuDto.setParentId(operatorResource.getParent().getId());
		menuDto.setResourceURL(operatorResource.getResourceURL());
		menuDto.setClick(operatorResource.getParent().getId()==0 ? Boolean.FALSE:Boolean.TRUE);
		return menuDto;
	}
}
