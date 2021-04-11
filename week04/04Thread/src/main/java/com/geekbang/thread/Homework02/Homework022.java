package com.geekbang.thread.Homework02;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.concurrent.Executors.*;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月10日 18:14
 */
public class Homework022 extends Thread {
    public static int result;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        // 创建一个newFixedThreadPool线程池，
        // 启动线程时会返回一个Future对象,通过future对象获取返回值
        ExecutorService fixedThreadPool = newFixedThreadPool(10);
        try {
            Future future = fixedThreadPool.submit(() -> sum());
            //在执行future.get()时，主线程会堵塞，直至当前future线程返回结果
            result = (int) future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        fixedThreadPool.shutdown();
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        System.out.println("主线线程退出");
        //异步计算结果为：24157817
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }
}
