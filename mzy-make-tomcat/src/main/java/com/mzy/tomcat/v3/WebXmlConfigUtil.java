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
 * ������
 * 
 * @author Administrator
 *
 */
public class WebXmlConfigUtil extends DefaultHandler {

	/* ����servlet class���� */
	Map<String, Object> servlets = new HashMap<>();

	// Servletӳ��
	Map<String, String> servletMapping = new HashMap<>();

	// Servletʵ������
	Map<String, Servlet> servletInstances = new HashMap<>();

	private String xmlPath;

	public WebXml loadXml(String xmlPath) throws Exception {
		this.xmlPath = xmlPath;
		// ����һ������XML�Ĺ��̶���
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		// ����һ������XML�Ķ���
		SAXParser saxParser = parserFactory.newSAXParser();
		// ����һ������������
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

	// ��ʼ�����ĵ�������ʼ����XML��Ԫ��ʱ���ø÷���
	@Override
	public void startDocument() throws SAXException {
		System.out.println("=================��ʼ�����ĵ�" + this.xmlPath);
	}

	// ��ʼ����ÿ��Ԫ��ʱ������øķ���
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// �ж����ڽ�����Ԫ���ǲ��ǿ�ʼ������Ԫ��
		this.qName = qName;
	}

	// ����ÿ��Ԫ�ص�����ʱ������ôη���
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String currentValue = new String(ch, start, length);
		// ������ݲ�Ϊ�պͿո�Ҳ���ǻ��з��򽫸�Ԫ������ֵ����map�С�
		if (currentValue == null || currentValue.trim().equals("")) {
			return;
		}

		if ("servlet-name".equals(qName)) {
			currentServlet = currentValue;
			currentServletMapping = currentValue;
		} else if (qName.equals("servlet-class")) {
			// servlet��Ϣ
			String servletClass = currentValue;
			servlets.put(currentServlet, servletClass);
		} else if (qName.equals("param-name")) {
			currentParam = currentValue;
		} else if (qName.equals("param-value")) {
			String paramValue = currentValue;
			// servlet param����
			HashMap<String, String> params = new HashMap<>();
			params.put(currentParam, paramValue);
		} else if (qName.equals("servlet-name")) {
			currentServletMapping = currentValue;
		} else if (qName.equals("url-pattern")) {
			String urlPattern = currentValue;
			// urlӳ��
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
