package com.mzy.tomcat.v3;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 工具类
 * 
 * @author Administrator
 *
 */
public class WebXmlConfigUtil extends DefaultHandler {

	/* 解析servlet class集合 */
	Map<String, Object> servlets = new HashMap<>();

	// Servlet映射
	Map<String, String> servletMapping = new HashMap<>();

	// Servlet实例集合
	Map<String, Servlet> servletInstances = new HashMap<>();

	private String xmlPath;

	public WebXml loadXml(String xmlPath) throws Exception {
		this.xmlPath = xmlPath;
		// 创建一个解析XML的工程独享
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		// 创建一个解析XML的独享
		SAXParser saxParser = parserFactory.newSAXParser();
		// 创建一个解析助手类
		saxParser.parse(this.xmlPath, this);

		WebXml webXml = new WebXml();
		webXml.servletInstances = this.servletInstances;
		webXml.servlets = this.servlets;
		webXml.servletMapping = this.servletMapping;
		return webXml;
	}

	String currentServlet = null;
	String currentServletMapping = null;
	String currentParam = null;
	String qName = null;

	// 开始解析文档，即开始解析XML根元素时调用该方法
	@Override
	public void startDocument() throws SAXException {
		System.out.println("=================开始解析文档" + this.xmlPath);
	}

	// 开始解析每个元素时都会调用改方法
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// 判断正在解析的元素是不是开始解析的元素
		this.qName = qName;
	}

	// 解析每个元素的内容时，会调用次方法
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String currentValue = new String(ch, start, length);
		// 如果内容不为空和空格，也不是换行符则将该元素名和值存入map中。
		if (currentValue == null || currentValue.trim().equals("")) {
			return;
		}

		if ("servlet-name".equals(qName)) {
			currentServlet = currentValue;
			currentServletMapping = currentValue;
		} else if (qName.equals("servlet-class")) {
			// servlet信息
			String servletClass = currentValue;
			servlets.put(currentServlet, servletClass);
		} else if (qName.equals("param-name")) {
			currentParam = currentValue;
		} else if (qName.equals("param-value")) {
			String paramValue = currentValue;
			// servlet param参数
			HashMap<String, String> params = new HashMap<>();
			params.put(currentParam, paramValue);
		} else if (qName.equals("servlet-name")) {
			currentServletMapping = currentValue;
		} else if (qName.equals("url-pattern")) {
			String urlPattern = currentValue;
			// url映射
			servletMapping.put(urlPattern, currentServletMapping);
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
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
