package com.study;
public class Two extends One{
    
	public static String one_1 = "two";

    public static void oneFn() {

        System.out.println("TwoFn");
    }
    
    public static class Three{
    	public static String one_1 = "three";
    }
	
    public static int hashCode(char[] value) {
        int h = 0;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
        }
        return h;
    }
    
    
	 //静态属性和静态方法是否可以被继承？是否可以被重写？以及原因？
    public static void main(String[] args) {
        One one = new Two();
        one.oneFn();
        String one_1 = One.one_1;
        System.out.println("One.one_1>>>>>>>"+one_1);
        String one_12 = one.one_1;
        System.out.println("one.one_1>>>>>>>"+one_12);
        
        char numChar = '君';
        int  intNum = numChar;
        System.out.println((int)numChar + ": " + intNum);

        System.out.println(Character.digit('7', 10));
        
        char[] cs = "I am 君山".toCharArray();
        
        String str = new String("222");
        char[] vs = str.toCharArray();
        System.out.println(hashCode(vs));
        String str1 = "222";
        String str2 = "222";
        System.out.println(str==str1);
        System.out.println(str.equals(str1));
        System.out.println(str.hashCode());
        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());
        System.out.println(str1==str2);
        System.out.println("abc".hashCode()+","+"bac".hashCode()+";"+"cab".hashCode());
    }
}
