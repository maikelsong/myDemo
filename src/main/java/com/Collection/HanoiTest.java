package com.Collection;
import java.util.Scanner;

//java处理汉诺塔问题

public class HanoiTest {
 public HanoiTest(){
  
 }
    public static void move(String from,String to){
     System.out.println("move the top plate from "+from+" to "+to);
    }
 public static void execute(int n,String from,String temp,String to){
  if(n==1){
   move(from,to);
  }else{
      execute(n-1,from,to,temp);
      move(from,to);
      execute(n-1,temp,from,to);
  }
 }
 /**
  * @param args
  */
 public static void main(String[] args) {
  // TODO Auto-generated method stub
  Scanner sc=null;
  try{
   sc = new Scanner(System.in); 
   System.out.print("请输入汉诺塔上盘子的初始数目：");
   while(sc.hasNext()){
    String s=sc.nextLine();
    if(!s.matches("//d+")){
     System.out.println("输入错误，请输入数字！");
    }
    int n=Integer.parseInt(s);
          execute(n,"A","B","C");
          System.out.print("请输入汉诺塔上盘子的初始数目：");        
   } 
  }catch(Exception e){
   e.printStackTrace();
  }finally{
   sc.close();
   System.out.println("End!");
  }
 }
}