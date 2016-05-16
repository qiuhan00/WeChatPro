package com.cfang.WeChat.utils;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
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

	/** header 常量定义 */
	private static final String ENCODING_DEFAULT = "UTF-8";
	private static final boolean NOCACHE_DEFAULT = true;

	public static void renderJson(HttpServletResponse response, Page<?> page,
			Map<String, Object> footer, String dataFormat,
			SimplePropertyPreFilter filter) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotalCount());
		map.put("rows", page.getResult());
		map.put("footer", new Map[] { footer });// easyui表格显示统计行
		String jsonResult = JSON.toJSONString(map, filter);
		render(response, jsonResult);
	}

	/**
	 * 纯文本直接输出内容
	 * 
	 * @param response
	 * @param json
	 */
	private static void render(HttpServletResponse response, String json) {
		PrintWriter out = null;
		try {
			String encoding = ENCODING_DEFAULT;
			boolean nocache = NOCACHE_DEFAULT;
			// 设置headers参数
		    String fullContentType = TEXT_TYPE + ";charset=" + encoding;
		    response.setContentType(fullContentType);
		    if (nocache) {
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
			}
		    out = response.getWriter();
		    out.write(json);
		    out.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}finally{
			out.close();
		}
	}
}
