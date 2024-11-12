package cn.itcast.mp.threadlock;

import java.util.concurrent.Semaphore;
/*

使用Semaphore作为锁

 */

public class SemaphoreExample {

    private static final Semaphore semaphore = new Semaphore(3); // 允许3个线程同时访问
    private static final int TOTAL_THREADS = 10;

    public static void main(String[] args) {
        for (int i = 0; i < TOTAL_THREADS; i++) {
            final int threadNumber = i;
            new Thread(() -> {
                try {
                    System.out.println("Thread " + threadNumber + " is trying to acquire a permit.");
                    semaphore.acquire(); // 获取一个许可证
                    System.out.println("Thread " + threadNumber + " has acquired a permit.");

                    // 模拟处理
                    Thread.sleep(2000); // 处理时间，不同的线程可能处理时间不同

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放许可证
                    System.out.println("Thread " + threadNumber + " is releasing a permit.");
                    semaphore.release(); // 释放许可证
                }
            }).start();
        }
    }
}