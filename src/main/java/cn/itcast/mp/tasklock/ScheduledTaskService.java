//package cn.itcast.mp.tasklock;
//
//import org.apache.curator.framework.recipes.locks.InterProcessMutex;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.concurrent.TimeUnit;
//
//
//
//@Service
//public class ScheduledTaskService {
//
//    @Autowired
//    private InterProcessMutex distributedLock;
//
//    //@Scheduled(fixedRate = 5000) // 每5秒执行一次
// //   @Scheduled(cron = "20 * * * * ?")
//    public void executeTask() {
//        boolean hasLock = false;
//        try {
//            // 尝试获取锁，最多等待 5 秒
//            LocalDateTime currentDateTime = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String formattedDateTime = currentDateTime.format(formatter);
//        int milliseconds = currentDateTime.getNano(); // 纳秒转为毫秒
//        System.out.println("时间=="+formattedDateTime+" " +milliseconds);
//
//            hasLock = distributedLock.acquire(5, TimeUnit.SECONDS);
//            if (hasLock) {
//                // 执行定时任务
//                System.out.println("成功获取锁"+"Executing task by: " + Thread.currentThread().getName());
//                // 模拟任务执行
//                System.out.println("执行任务");
//                Thread.sleep(3000);
//
//            } else {
//                System.out.println("没有获取锁");
//                System.out.println("Couldn't acquire lock, task not executed by: " + Thread.currentThread().getName());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (hasLock) {
//                try {
//                    // 释放锁
//                    distributedLock.release();
//                    System.out.println("释放锁");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}