package com.weakRef;

import java.lang.ref.PhantomReference;    
import java.lang.ref.Reference;    
import java.lang.ref.ReferenceQueue;    
import java.lang.reflect.Field;    
   
public class PTest {    
    public static boolean isRun = true;    
   
    public static void main(String[] args) throws Exception {    
        String abc = new String("abc");    
        System.out.println(abc.getClass() + "@" + abc.hashCode());    
        final ReferenceQueue referenceQueue = new ReferenceQueue<String>();    
        new Thread() {    
            public void run() {    
                while (isRun) {    
                    Object o = referenceQueue.poll();    
                    if (o != null) {    
                        try {    
                            Field rereferent = Reference.class   
                                    .getDeclaredField("referent");    
                            rereferent.setAccessible(true);    
                            Object result = rereferent.get(o);    
                            System.out.println("gc will collect:"   
                                    + result.getClass() + "@"   
                                    + result.hashCode());    
                        } catch (Exception e) {    
   
                            e.printStackTrace();    
                        }    
                    }    
                }    
            }    
        }.start();    
        PhantomReference<String> abcWeakRef = new PhantomReference<String>(abc,    
                referenceQueue);    
        abc = null;    
        Thread.currentThread().sleep(3000);    
        System.gc();    
        Thread.currentThread().sleep(3000);    
        isRun = false;    
    }    
   
}
