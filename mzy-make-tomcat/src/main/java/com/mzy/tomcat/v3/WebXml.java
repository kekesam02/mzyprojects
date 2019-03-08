package com.mzy.tomcat.v3;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;

/**
 * �������
 * @author Administrator
 *
 */
public class WebXml {

	//��Ŀ�ļ��е�ַ
	public String projectPath = null;
	//servlet����
	/*����servlet class����*/
	Map<String,Object> servlets = new HashMap<>();
	
	//Servletӳ��
	Map<String,String> servletMapping = new HashMap<>();
	
	//Servletʵ������
	Map<String,Servlet> servletInstances= new HashMap<>();
	
	private URLClassLoader loader = null;
	
	
	
	public void loadServlet() throws Exception {
		loader  = new URLClassLoader(new URL[] {
			new URL("file:"+projectPath+"\\WEB-INF\\classes\\")	
		});
		
		//����webxml�е�servlet class����
		for (Map.Entry<String, Object> entry:servlets.entrySet()) {
			String servletName = entry.getKey();
			String servletClass = entry.getValue().toString();
			//1������class��jvm
			Class<?> servletClazz = loader .loadClass( servletClass);
			//��������
			Servlet servlet = (Servlet) servletClazz.newInstance();
			//���浽servlet������
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
