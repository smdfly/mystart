package cn.itcast.mp.zookeeperlock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

public class DistributedLock {

    private final InterProcessMutex lock;

    public DistributedLock(CuratorFramework client, String lockPath) {
        this.lock = new InterProcessMutex(client, lockPath);
    }

    // 尝试获取锁
    public boolean tryLock() {
        try {
            return lock.acquire(2000, TimeUnit.MILLISECONDS); // 2秒超时
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 释放锁
    public void unlock() {
        try {
            lock.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String  GetProcessId() {
        // 获取当前运行的进程 ID
        String processId = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];

        System.out.println("当前进程 ID: " + processId);
        return  processId;

    }
}