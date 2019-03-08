package com.mzy.tomcat.v3;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;

/**
 * 存放配置
 * @author Administrator
 *
 */
public class WebXml {

	//项目文件夹地址
	public String projectPath = null;
	//servlet集合
	/*解析servlet class集合*/
	Map<String,Object> servlets = new HashMap<>();
	
	//Servlet映射
	Map<String,String> servletMapping = new HashMap<>();
	
	//Servlet实例集合
	Map<String,Servlet> servletInstances= new HashMap<>();
	
	private URLClassLoader loader = null;
	
	
	
	public void loadServlet() throws Exception {
		loader  = new URLClassLoader(new URL[] {
			new URL("file:"+projectPath+"\\WEB-INF\\classes\\")	
		});
		
		//遍历webxml中的servlet class定义
		for (Map.Entry<String, Object> entry:servlets.entrySet()) {
			String servletName = entry.getKey();
			String servletClass = entry.getValue().toString();
			//1：加载class到jvm
			Class<?> servletClazz = loader .loadClass( servletClass);
			//创建对象
			Servlet servlet = (Servlet) servletClazz.newInstance();
			//保存到servlet集合中
			servletInstances.put(servletName, servlet);
		}
		
	}

	public Map<String, Object> getServlets() {
		return servlets;
	}

	public void setServlets(Map<String, Object> servlets) {
		this.servlets = servlets;
	}

	public Map<String, String> getServletMapping() {
		return servletMapping;
	}

	public void setServletMapping(Map<String, String> servletMapping) {
		this.servletMapping = servletMapping;
	}

	public Map<String, Servlet> getServletInstances() {
		return servletInstances;
	}

	public void setServletInstances(Map<String, Servlet> servletInstances) {
		this.servletInstances = servletInstances;
	}
}
