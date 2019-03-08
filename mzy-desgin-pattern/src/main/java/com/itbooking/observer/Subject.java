package com.itbooking.observer;
import java.util.ArrayList;
import java.util.List;
 
public class Subject {
   
   private List<Observer> observers 
      = new ArrayList<Observer>();
   private int state;
 
   public int getState() {
      return state;
   }
 
   //监听状态
   public void setState(int state) {
      this.state = state;
      notifyAllObservers();
   }
 
   //添加相关订阅类到集合中
   public void attach(Observer observer){
      observers.add(observer);      
   }
 
   //循环执行
   public void notifyAllObservers(){
      for (Observer observer : observers) {
         observer.update();
      }
   }  
}