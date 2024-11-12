package cn.itcast.mp.threadlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 */
public class ReentrantLockCounter {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock(); // 获取锁
        try {
            count++;
        } finally {
            lock.unlock(); // 确保释放锁
        }
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        ReentrantLockCounter counter = new ReentrantLockCounter();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
                System.out.println(Thread.currentThread().getName()+"----"+counter.getCount());
            }
        };

        Thread thread1 = new Thread(task);
        thread1.setName("thread-1");
        Thread thread2 = new Thread(task);
        thread2.setName("thread-2");

        thread1.start();
        thread2.start();

//        try {
//            thread1.join();
//            thread2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println("Final count: " + counter.getCount());
    }
}