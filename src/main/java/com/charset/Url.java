package com.charset;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


public class Url {

    public static void main(String[] args) throws UnsupportedEncodingException {


        String s1 = URLEncoder.encode("中国");
        System.out.println(s1);

        //字母数字字符 "a" 到 "z"、"A" 到 "Z" 和 "0" 到 "9" 保持不变。
        //特殊字符 "."、"-"、"*" 和 "_" 保持不变。
        //空格字符 " " 转换为一个加号 "+"。
        //所有其他字符都是不安全的，因此首先使用一些编码机制将它们转换为一个或多个字节

        String s2 = URLEncoder.encode(":/()+=._-*", "utf-8");
        System.out.println(s2);

        System.out.println(URLDecoder.decode(s2, "utf-8"));

        String array = java.util.Arrays.toString("中国".getBytes("utf-8"));
        System.out.println(array);

        String arrays[] = array.substring(1, array.length() - 1).split(",");

        System.out.println(Integer.toHexString(17));

        for (String s : arrays) {
            System.out.println(s + ">" + Integer.toHexString(new Integer(s.trim())).toUpperCase());
        }

        String t = URLEncoder.encode(".", "utf-8");
        System.out.println("t==" + t);

        int i = 50;
        System.out.println(i + "");
        System.out.println(String.valueOf(i));


        //http%3A%2F%2Fraolesong.dev.ztgame.com%2FMobileHelperService%2FLoginServlet%3Faction%3Dlogin%26account%3Dtestaaaaaa%26pwd%3D1BFEA4D81086A297%26idnum%3D%C8%C4%C0%D6%CB%C9
        //http%3A%2F%2Fraolesong.dev.ztgame.com%2FMobileHelperService%2FLoginServlet%3Faction%3Dlogin%26account%3Dtestaaaaaa%26pwd%3D1BFEA4D81086A297%26idnum%3D%E9%A5%B6%E4%B9%90%E6%9D%BE

//		System.out.println(s1);


//		System.out.println(java.util.Arrays.toString("中国移动".getBytes("utf-8")));

//		String s3 = URLDecoder.decode(s1, "gbk");
//		String s4 = URLDecoder.decode(s2, "utf-8");
//		
//		System.out.println(s3);
//		System.out.println(s4);


        //火狐-饶乐松
        //%C8%C4%C0%D6%CB%C9
    }

}
