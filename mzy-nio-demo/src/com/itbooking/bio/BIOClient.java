package com.itbooking.bio;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

public class BIOClient implements Runnable{
	
	private String host;
	private int port;
	private Charset charset = Charset.forName("UF-8");
	
	public BIOClient(String host,int port) {
		super();
		this.host = host;
		this.port = port;
	}
	
	@Override
	public void run() {
		try (Socket socket = new Socket(host, port);OutputStream out = socket.getOutputStream()){
			Scanner scanner = new Scanner(System.in);
			System.out.println("«Î ‰»Î:");
			String mess = scanner.nextLine();
			out.write(mess.getBytes(charset));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		BIOClient client = new BIOClient("localhost", 9010);
		client.run();
	}
}
