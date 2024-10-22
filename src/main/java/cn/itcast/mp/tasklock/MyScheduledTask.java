//package cn.itcast.mp.tasklock;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MyScheduledTask {
//
//    @Autowired
//    private LockService lockService;
//
//    private static final String LOCK_NAME = "my_task_lock";
//
//   // @Scheduled(fixedRate = 20000) // 每 20秒执行一次
//   @Scheduled(cron = "20 * * * * ?")
//   public void performTask() throws InterruptedException {
//        if (lockService.acquireLock(LOCK_NAME, 10)) { // 获取锁，超时设置为 10 秒
//            try {
//                // 执行业务逻辑
//                System.out.println("执行任务...");
//                // 模拟任务处理时间
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            } finally {
//                // 释放锁
//                lockService.releaseLock(LOCK_NAME);
//            }
//        } else {
//            System.out.println("无法获取锁，任务被跳过");
//        }
//    }
//}