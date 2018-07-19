package com.study;
/**
 * 闭包（Closure）是一种能被调用的对象，它保存了创建它的作用域的信息。JAVA并不能显式地支持闭包，但是在JAVA中，闭包可以通过“接口+内部类”来实现。
	例如：一个接口程序员和一个基类作家都有一个相同的方法work，相同的方法名，但是其含义完全不同，这时候就需要闭包。
	在子类中定义了遵循程序员接口规则的内部类，然后使用内部类实现程序员的work()方法回调code()方法，在子类中直接实现父类作家的work()方法。
 * @author raolesong
 * 2018年7月17日 下午5:10:45
 */
public class WriterProgrammer extends Writer {
 @Override
 public void work(){
   //写作
 }
 public void code(){
   //写代码
 }
 class ProgrammerInner implements programmer{
   @Override
   public void work(){
     code();
   }
 }
}