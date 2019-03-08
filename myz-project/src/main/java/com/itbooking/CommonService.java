package com.itbooking;

public class CommonService {

	private String name;
	private int time;
	
	public void service() {
		System.out.println(name+"===="+time);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

}
