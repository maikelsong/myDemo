package com.thread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 普通的Collection的另一个隐患
 * 1【非线程安全的】
 * 2 在传统的collection在迭代【读】的过程中，不允许去对集合数据修改。
 * 根据分析AbstractList的checkForComodification方法的源码分析产生ConcurrentModificationException
 * 我们用CopyOnWriteArrayList去代替
 * <p/>
 * ConcurrentHashMap
 * CopyOnWriteArrayList
 * CopyOnWriteArraySet
 * <p/>
 * <p/>
 * 类说明
 *
 * @author raolesong
 * @version V 1.0 Dec 5, 2011
 */

public class CollectionModifyExceptionTest {
    public static void main(String[] args) {

//		Collection users = new CopyOnWriteArrayList();
        Collection users = new ArrayList();

        users.add(new User("张三", 28));
        users.add(new User("李四", 25));
        users.add(new User("王五", 31));
        Iterator itrUsers = users.iterator();
        while (itrUsers.hasNext()) { //在迭代【读】的过程中，去remove。可能出问题的。
            System.out.println("aaaa");
            User user = (User) itrUsers.next();
            if ("张三".equals(user.getName())) {
//			if("李四".equals(user.getName())){
//			if("王五".equals(user.getName())){
                users.remove(user);
                //itrUsers.remove();
            } else {
                System.out.println(user);
            }
        }
    }
}	 
