package com.mzy.tomcat.v3;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * �������
 * @author Administrator
 *
 */
public class ProjectUtil {

	public  static Map<String, WebXml> result = new HashMap<String, WebXml>();
	 
	public static void load() throws Exception {
		String webapps = "d://tomcatapps";
		//��ѹwar
		//�����ļ��У�ÿ���ļ��ж���ǰһ����Ŀ
		File[] listFiles = new File(webapps ).listFiles(File::isDirectory);
		for (File project : listFiles) {
			//��ȡweb.xml�ļ�����������servlet��servlet��Ӧ·��ƥ�����
			WebXml webXml = new WebXmlConfigUtil().loadXml(project.getPath()+"\\WEB-INF\\web.xml");
			webXml.projectPath = project.getPath();
			//����class(����ؼ���)��������
			webXml.loadServlet();
			//��ӵ������У�׼���������жϺ͵���
			result.put(project.getName(), webXml);
		}
	}
	
}
