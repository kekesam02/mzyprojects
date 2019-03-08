package com.itbooking.observer;
public class ObserverPatternDemo {
   public static void main(String[] args) {
	  //主题
      Subject subject = new Subject();
 
      //订阅主题 subject----订阅的过程其实就在放入到集合的过程
      new HexaObserver(subject);
      //订阅主题 subject
      new OctalObserver(subject);
      //订阅主题 subject
      new BinaryObserver(subject);
 
      //发布主题
      System.out.println("First state change: 15");   
      //发布的过程，其实就把集合中的对象去执行一遍。
      subject.setState(15);
      //System.out.println("Second state change: 10");  
      //subject.setState(10);
   }
}