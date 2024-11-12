package cn.itcast.mp.threadlock;

public class SynchronizedCounter {
    private int count = 0;
    private  Object lock = new Object();

    // 同步方法
    public synchronized void increment() {
        count++;
    }

    public void someMethod() {
        synchronized (lock) {
            this.count++;
            // 线程安全的代码
        }
    }

    public void someMethod2() {
        synchronized (this) {
            this.count++;
            // 线程安全的代码
        }
    }

    public int getCount() {
        return count;
    }
}

