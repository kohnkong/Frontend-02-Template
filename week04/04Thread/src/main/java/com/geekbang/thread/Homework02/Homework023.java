package com.geekbang.thread.Homework02;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月10日 18:14
 */
public class Homework023 extends Thread {
    public static int result;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        // 创建一个newFixedThreadPool线程池，
        // 启动线程时会返回一个Future对象,通过future对象获取返回值
        Runnable task = () -> {
            result = sum();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                Thread t = Thread.currentThread();
                System.out.println("当前线程：" + t.getName());
            }
        };
        Thread thread = new Thread(task);
        thread.setName("test-thread-1");
        //thread.setDaemon(true); // 守护线程
        thread.start();
        Thread.sleep(5500);
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
