package com.mzy.tomcat.v3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

public class TomcatServerV3 {

	private static ExecutorService threadPool = Executors.newCachedThreadPool();
	private static int port = 8080;
	
	public static void main(String[] args) throws Exception {
		
		//加载项目
		ProjectUtil.load();
		//scoket  网络编程-BIO
		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("Tomcat服务启动成功,端口号是："+port);
		while (!serverSocket.isClosed()) {
			//定义接受的请求对象
			Socket request = serverSocket.accept();
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						//接受打印数据
						InputStream inputStream = request.getInputStream();
						System.out.println("接受到了请求了....");
						BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
						String message = null;
						while((message =reader.readLine())!=null) {
							if(message.length()==0) {
								break;
							}
							System.out.println(message);
						}
						
						//触发业务代码执行，根据用户请求调用servlet
						//GET /servlet-demo-1.0.0/index HTTP/1.1 根据用户请求，调用servlet
						//解析http请求，拿到请求所对应的项目，通知，调用servlet
						WebXml webXml = ProjectUtil.result.get("myz-tomcat-servlet");
						//根据请求路径
						String servletName = webXml.servletMapping.get("/index");
						Servlet servlet = webXml.servletInstances.get(servletName);
						
						//完成servlet声明周期的事情
						HttpServletRequest req = createRequest();
						HttpServletResponse res = createResponse();
						ServletConfig config = null;
						//初始化
						servlet.init(config);
						//调用service方法
						servlet.service(req, res);
						
						
						
						
						System.out.println("-----------------request end----------------------");
						//响应结构200
						String responseValue = "Hello World";
						OutputStream outputStream = request.getOutputStream();
						outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
						outputStream.write(("Content-Length:"+(responseValue.length())+"\r\n\r\n").getBytes());
						outputStream.write(responseValue.getBytes());
						outputStream.flush();
						
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						try {
							request.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			});
		}
	}
	
	
	public static HttpServletRequest createRequest() {
		return new HttpServletRequest() {
			
			@Override
			public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
					throws IllegalStateException {
				return null;
			}
			
			@Override
			public AsyncContext startAsync() throws IllegalStateException {
				return null;
			}
			
			@Override
			public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
				
			}
			
			@Override
			public void setAttribute(String name, Object o) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void removeAttribute(String name) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isSecure() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isAsyncSupported() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isAsyncStarted() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public ServletContext getServletContext() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getServerPort() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getServerName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getScheme() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public RequestDispatcher getRequestDispatcher(String path) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getRemotePort() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getRemoteHost() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getRemoteAddr() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getRealPath(String path) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public BufferedReader getReader() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getProtocol() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String[] getParameterValues(String name) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Enumeration<String> getParameterNames() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Map<String, String[]> getParameterMap() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getParameter(String name) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Enumeration<Locale> getLocales() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Locale getLocale() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getLocalPort() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getLocalName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getLocalAddr() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ServletInputStream getInputStream() throws IOException {
				return null;
			}
			
			@Override
			public DispatcherType getDispatcherType() {
				return null;
			}
			
			@Override
			public String getContentType() {
				return null;
			}
			
			@Override
			public long getContentLengthLong() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int getContentLength() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getCharacterEncoding() {
				return null;
			}
			
			@Override
			public Enumeration<String> getAttributeNames() {
				return null;
			}
			
			@Override
			public Object getAttribute(String name) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public AsyncContext getAsyncContext() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <T extends HttpUpgradeHandler> T upgrade(Class<T> handlerClass) throws IOException, ServletException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void logout() throws ServletException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void login(String username, String password) throws ServletException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isUserInRole(String role) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isRequestedSessionIdValid() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isRequestedSessionIdFromUrl() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isRequestedSessionIdFromURL() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isRequestedSessionIdFromCookie() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Principal getUserPrincipal() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public HttpSession getSession(boolean create) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public HttpSession getSession() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getServletPath() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getRequestedSessionId() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public StringBuffer getRequestURL() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getRequestURI() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getRemoteUser() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getQueryString() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getPathTranslated() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getPathInfo() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Collection<Part> getParts() throws IOException, ServletException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Part getPart(String name) throws IOException, ServletException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getMethod() {
				return "POST";
			}
			
			@Override
			public int getIntHeader(String name) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Enumeration<String> getHeaders(String name) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Enumeration<String> getHeaderNames() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getHeader(String name) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public long getDateHeader(String name) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Cookie[] getCookies() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getContextPath() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getAuthType() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String changeSessionId() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
				// TODO Auto-generated method stub
				return false;
			}
		};
	}
	
	public static HttpServletResponse createResponse() {
		return new HttpServletResponse() {
			
			@Override
			public void setLocale(Locale loc) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setContentType(String type) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setContentLengthLong(long len) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setContentLength(int len) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setCharacterEncoding(String charset) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setBufferSize(int size) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void resetBuffer() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isCommitted() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public PrintWriter getWriter() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ServletOutputStream getOutputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Locale getLocale() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getCharacterEncoding() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getBufferSize() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public void flushBuffer() throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setStatus(int sc, String sm) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setStatus(int sc) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setIntHeader(String name, int value) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setHeader(String name, String value) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setDateHeader(String name, long date) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendRedirect(String location) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendError(int sc, String msg) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendError(int sc) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public int getStatus() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Collection<String> getHeaders(String name) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Collection<String> getHeaderNames() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getHeader(String name) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String encodeUrl(String url) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String encodeURL(String url) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String encodeRedirectUrl(String url) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String encodeRedirectURL(String url) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean containsHeader(String name) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void addIntHeader(String name, int value) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addHeader(String name, String value) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addDateHeader(String name, long date) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addCookie(Cookie cookie) {
				// TODO Auto-generated method stub
				
			}
		};
		
	}
	
	
}
