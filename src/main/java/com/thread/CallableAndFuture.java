package com.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/*
 * Callable 也是个任务，用threadPool.submit提交一个任务，【和threadPool.execute提交任务一样，只是这样提交任务，这个线程执行任务没有返回值】，
 * 而threadPool.submit提交任务是有返回值的。返回一个Future
 * 
 * 
 * 
 */

public class CallableAndFuture {

    /**
     * @param args
     * @throws TimeoutException 
     * @throws ExecutionException 
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<String> future =
                threadPool.submit(
                        new Callable<String>() {
                            public String call() throws Exception {
                                System.out.println("执行一些任务");
                                Thread.sleep(10000);
                                return "hello";
                            }

                            ;
                        }
                );


        System.out.println("等待结果");
//        try {
//            System.out.println("拿到结果：" + future.get()); //一直等待。等到结果
			System.out.println("拿到结果：" + future.get(2,TimeUnit.SECONDS));  //1000之内没有返回结果，就抛异常
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

        //提交一组Callable任务。所以需要一个线程池
        //CompletionService表示能获取到最先完成的任务的序列

        ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool2);
        for (int i = 1; i <= 10; i++) { //提交10个任务。
            final int seq = i;
            completionService.submit(new Callable<Integer>() {

                public Integer call() throws Exception {
                    Thread.sleep(new Random().nextInt(5000));
                    return seq;
                }
            });
        }

        //任务提交完成，，等待收获了。。。 completionService表示能获取到最先完成的任务的序列

        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(
                        completionService.take().get());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


}
