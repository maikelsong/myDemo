package com.suanfa;
import java.util.Scanner;

public class MonkeyDemo {

	public static void main(String[] args) {
	       int PeachNum = 4;      
	       int MonkeyNum = 5;      
	       int FifthPeach = 0;      
	       while(true)
	       {
	           int flag=1;
	           PeachNum = MonkeyNum*FifthPeach+1;                               //这里用来推导第五次分拣之前的桃子数量 通过数学关系分析得出
	           for(int i = MonkeyNum;i>=1;i--){                                 //告诉计算机需要进行猴子数量次数的迭代
	               if(PeachNum % (MonkeyNum-1) == 0) {                            
	                   PeachNum = PeachNum / (MonkeyNum - 1) * MonkeyNum + 1;      //迭代，建立剩余桃子数量的关系，从后向前进行迭代
	                   flag++;
	               }
	               else break;
	           }
	               if(flag==MonkeyNum)                                           //告诉计算机迭代次数别做多了 做到5次自动跳出
	                   break;
	               FifthPeach=FifthPeach+5;

	       }
	        System.out.println("总共桃子数量"+PeachNum);
	        System.out.println("第五只猴子分到桃子数量"+FifthPeach);

	       }
	    }