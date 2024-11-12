package cn.itcast.mp.threadlock;

public class TestSynchronized {
    public static void main(String[] args) {
        SynchronizedCounter counter = new SynchronizedCounter();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
                System.out.println(Thread.currentThread().getName() + "----" + counter.getCount());
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

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
