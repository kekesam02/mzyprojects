package com.itbooking.observer;

//定义观察者类
public abstract class Observer {
   //给子类去继承使用
   protected Subject subject;
   //抽象方法，让子类去实现覆盖的部分
   public abstract void update();
}