package com.mzy.tomcat.v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TomcatServerV2 {

	private static ExecutorService threadPool = Executors.newCachedThreadPool();
	private static int port = 8080;
	
	public static void main(String[] args) throws IOException {
		//scoket  ������-BIO
		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("Tomcat���������ɹ�,�˿ں��ǣ�"+port);
		while (!serverSocket.isClosed()) {
			//������ܵ��������
			Socket request = serverSocket.accept();
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						//���ܴ�ӡ����
						InputStream inputStream = request.getInputStream();
						System.out.println("���ܵ���������....");
						BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
						String message = null;
						while((message =reader.readLine())!=null) {
							if(message.length()==0) {
								break;
							}
							System.out.println(message);
						}
						
						System.out.println("-----------------request end----------------------");
						//��Ӧ�ṹ200
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
}
