package com.mzy.tomcat.v3;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 存放配置
 * @author Administrator
 *
 */
public class ProjectUtil {

	public  static Map<String, WebXml> result = new HashMap<String, WebXml>();
	 
	public static void load() throws Exception {
		String webapps = "d://tomcatapps";
		//解压war
		//遍历文件夹，每个文件夹都当前一个项目
		File[] listFiles = new File(webapps ).listFiles(File::isDirectory);
		for (File project : listFiles) {
			//读取web.xml文件，解析里面servlet和servlet对应路径匹配规则
			WebXml webXml = new WebXmlConfigUtil().loadXml(project.getPath()+"\\WEB-INF\\web.xml");
			webXml.projectPath = project.getPath();
			//加载class(类加载技术)，创对象
			webXml.loadServlet();
			//添加到容器中，准备后续的判断和调用
			result.put(project.getName(), webXml);
		}
	}
	
}
