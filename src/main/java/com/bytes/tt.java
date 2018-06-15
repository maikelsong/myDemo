package com.bytes;




import java.awt.image.BufferedImage;
import java.awt.*;

import com.sun.image.codec.jpeg.*;

import java.io.*;
import java.nio.ByteOrder;

/**
 * 计算器里面选择  “【双字】     32位”  
 * 
 * 
 * 
 * 10000 = 2的4次方 = 16【十进制】 【最右边为第0为，依次过来是第4位为1，其他位为0】
 * <p/>
 * [0x]：表示该值为16进制。
 * [0xff] : 255
 * <p/>
 * 【【【【【【【【【【【【【【【【一共有6种位运算】】】】】】】】】】】】】】】
 * 1) &：位上全1 即 1,其他即 0
 * 2) |:位上有一个1，即1，其他即0
 * 3) ^  异或运算符＾又被称为XOR运算符。当参与运算的两个位相同（‘1’与‘1’或‘0’与‘0’）时结果为‘0’。不同 为‘1’。即 【相同为0，不同为1】。
 * 　　　　　	 0^0=0; 0^1=1; 1^0=1;1^1=0;
 * 4) ~ :起反；很好理解，1变0,0变1
 * 
 * 5) <<  [左移运算符] 貌似变大 ？
 * 6  >>  [右移运算符] 貌似变小 ？
 *  
 *  
 *  2的8次方=256
 *  1 >>= 8
 *  1 >>= 8;
 *  
 *  <<=     左移后赋值,变量<<=表达式 左移就是将《左边的数的二进制各位全部左移若干位，《右边的数指定移动位数，高位丢弃，低位补0， 移几位就相当于乘以2的几次方
 *  >>=
 *  
 * 
 * 在许多计算机编程语言（例如：C语言、C++语言、Java语言、JavaScript语言、Pascal语言等）中，“>>”代表右移运算符，
 * 就相当于“shr”。该运算符为双目运算符，结合方向为从左到右，
 * 作用是把一个整型数的所有位向右移动指定的位数，移动到右边界之外的多余二进制位会被丢弃，并从左边界移入0。
 * 
 *  问：计算表达式14 >> 2的值。
	答：表达式14 >> 2的值为3，因为14（即二进制的00001110）向右移两位等于3（即二进制的00000011）。
说白了，就是把要移动的数转换成2进制，右移几位就去掉右边的几位数，左移几位就在右边加几个0，
比如14右移2位就是转成二进制变成1110，去掉右边的10，变成11，11转成十进制就是3；左移2位就是111000，转成十进制就是56。
 * 
 * 
 * 下面的a和b都是整数类型，则：
	    a and b | a&b　| a&b
		a or b | a|b　 | a|b
		a xor b | a ^ b　| a ^b
		not a | ~a　 | ~a
		a shl b | a << b | a << b
		a shr b | a >> b | a>>b 无符号右移
		- | - | a>>> b 带符号右移
 * 
 * 【&, |, ^, 是双目运算符】
 * 【~，<< , >>,是单目运算 】
 * <p/>
 * 我们拿255来看
 * 0000 0000 0000 0000 0000 0000 1111 1111 【共32位】
 * <p/>
 * 类说明
 * 
 * C语言中位运算符之间，按优先级顺序排列为
	1 ~
	2 <<、>>
	3 &
	4 ^
	5 |
	6 &=、^=、|=、<<=、>>=
	
 * 
 * 
 *
 * @author raolesong
 * @version V 1.0 Jul 2, 2012
 */

public class tt {

    public static void main(String[] args) {


    	System.out.println(ByteOrder.nativeOrder().toString());
    	
    	
        System.out.println(32 | 0x20);
        System.out.println((32 | 0x20) + " <-");

        int i = 128 | 0x80;
        System.out.println(i);

        int j = 128 & (~0x80);
        System.out.println("j=" + j);

        int m = 128 & 0x2;
        System.out.println(m == 0);


        int n = 128 & 0x80;
        System.out.println(n == 0);

		/*
		 * 我们来看一个例子，我们拿一个数255我们先把它取反
		   0000 0000 0000 0000 0000 0000 1111 1111
		    取反后；
		   1111 1111 1111 1111 1111 1111 0000 0000
		 * 
		 */

        System.out.println(~255); //取反 -256  为什么呢？ 【双字】
		
		/*
		 * 我们再把它向右移5位，结果应为
		   0000 0111 1111 1111 1111 1111 1111 1000
		 */

        System.out.println((~255) >> 5);

        /**
         *   异或运算符＾又被称为XOR运算符。当参与运算的两个位相同（‘1’与‘1’或‘0’与‘0’）时结果为‘0’。不同 为‘1’。即 【相同为0，不同为1】。
         　　　　　	 0^0=0; 0^1=1; 1^0=1;1^1=0;
         */
        System.out.println(255 ^ 255);
        System.out.println(255 ^ 1);

    }

}