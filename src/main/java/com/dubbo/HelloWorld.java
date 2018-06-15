package com.dubbo;
public class HelloWorld implements Hello{  
  
    @Override  
    public String sayHello(String message) {  
        System.out.println("hello..."+message+"!");  
        return "hello..."+message+"!";  
    }  
    @Override  
    public void sayYes(String msg) {  
        System.out.println("yes: "+msg+"...");  
          
    }  
}  