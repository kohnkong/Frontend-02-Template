package com.geekbang.thread.Homework02;


/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月10日 18:14
 */
public class Homework021 extends Thread {
    public static int result;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                result = sum(); //这是得到的返回值
            }
        });
        thread.start();
        thread.join(); //阻塞
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        System.out.println("主线线程退出");
        //异步计算结果为：24157817
        // 然后退出main线程
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
