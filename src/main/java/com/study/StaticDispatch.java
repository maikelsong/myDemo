package com.study;
public class StaticDispatch {

    public static void main(String[] args) {
    	StaticDispatch sr = new StaticDispatch();
        Human man = sr.new Man();
        Human woman = sr.new Woman();
        
        sr.sayHello(man);
        sr.sayHello(woman);
    }

      class Human { }

      class Man extends Human { }

      class Woman extends Human { }

    public void sayHello(Human guy) {
        System.out.println("hello, guy");
    }

    public void sayHello(Man guy) {
        System.out.println("hello, man");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello, woman");
    }
}