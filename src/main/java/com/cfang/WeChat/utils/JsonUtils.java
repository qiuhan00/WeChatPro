package com.cfang.WeChat.utils;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

/**
 * json工具类、response工具类
 * 
 * @author Alex.Fang
 * @version 2016-5-16 下午2:37:57
 */
public class JsonUtils {
	
	private static Logger logger = Logger.getLogger(JsonUtils.class); 
	
	/** content-type 常量定义 */
	private static final String TEXT_TYPE = "text/plain";
	private static final String JSON_TYPE = "application/json";

	/** header 常量定义 */
	private static final String ENCODING_DEFAULT = "UTF-8";
	private static final boolean NOCACHE_DEFAULT = true;
	
	/** EasyUI专用方法和常量 */
	public static String JSON_NAME = "_easy_grid";
	public static String INSERT_RECORDS_KEY = "_inserted";
	public static String UPDATE_RECORDS_KEY = "_updated";
	public static String DELETE_RECORDS_KEY = "_deleted";

	public static void renderJson(HttpServletResponse response, Page<?> page,
			Map<String, Object> footer, String dataFormat,
			SimplePropertyPreFilter filter) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotalCount());
		map.put("rows", page.getResult());
		if(null != footer && !footer.isEmpty()){
			map.put("footer", new Map[] { footer });// easyui表格显示统计行
		}
		SerializerFeature[] feature = {SerializerFeature.WriteDateUseDateFormat};
		String jsonResult = JSON.toJSONString(map, filter, feature);
		render(response, jsonResult, TEXT_TYPE);
	}
	
	//菜单序列化
	public static void renderJson(HttpServletResponse response, Collection<?> object){
//		render(response, JSONArray.parseArray(object.toString()));
		render(response, JSONArray.fromObject(object), TEXT_TYPE);
	}
	
	/**
	 * 返回成功信息 {'success':successInfo}
	 * @param successInfo
	 */
	public static void renderSuccess(String successInfo, HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("success", successInfo);
		render(response, JSONObject.toJSON(map).toString(), JSON_TYPE);
	}
	
	/**
	 * 返回失败信息 {'failure':failInfo}
	 * @param failInfo
	 */
	public static void renderFailure(String failInfo, HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("failure", failInfo);
		render(response, JSONObject.toJSON(map).toString(), JSON_TYPE);
	}

	/**
	 * 纯文本直接输出内容
	 * 
	 * @param response
	 * @param json
	 */
	private static void render(HttpServletResponse response, Object json, String contentType) {
		PrintWriter out = null;
		try {
			String encoding = ENCODING_DEFAULT;
			boolean nocache = NOCACHE_DEFAULT;
			// 设置headers参数
		    String fullContentType = contentType + ";charset=" + encoding;
		    response.setContentType(fullContentType);
		    if (nocache) {
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
			}
		    out = response.getWriter();
		    out.print(json);
		    out.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}finally{
			out.close();
		}
	}
	
	/**
	 * 解析表格新增、修改、删除行记录
	 */
	public static <T> List<T> getInsertRecords(Class<T> cls, HttpServletRequest request){
		return getJsonObject(cls, INSERT_RECORDS_KEY, request);
	}
	public static <T> List<T> getUpdatedRecords(Class<T> cls, HttpServletRequest request){
		return getJsonObject(cls, UPDATE_RECORDS_KEY, request);
	}
	public static <T> List<T> getDeletedRecords(Class<T> cls, HttpServletRequest request){
		return getJsonObject(cls, DELETE_RECORDS_KEY, request);
	}
	
	private static <T> List<T> getJsonObject(Class<T> cls, String key, HttpServletRequest request) {
		String json = request.getParameter(JSON_NAME);
		JSONObject object = JSONObject.parseObject(json);
		if(null != object){
			return JSONObject.parseArray(object.get(key).toString(), cls);
		}else{
			return new ArrayList<T>();
		}
	}
}
