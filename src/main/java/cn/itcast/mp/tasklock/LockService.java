//package cn.itcast.mp.tasklock;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//@Service
//public class LockService {
//
//    @Autowired
//    private LockMapper lockMapper;
//
//    @Transactional
//    public boolean acquireLock(String lockName, long timeout) throws InterruptedException {
//        long startTime = System.currentTimeMillis();
//        LocalDateTime currentDateTime = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String formattedDateTime = currentDateTime.format(formatter);
//        int milliseconds = currentDateTime.getNano() / 1_000_000; // 纳秒转为毫秒
//
//        System.out.println("时间=="+formattedDateTime+"  " +milliseconds);
//
//
//        while (System.currentTimeMillis() - startTime < timeout * 1000) {
//            System.out.println("循环获取锁");
//            // 检查锁是否已经存在
//            try {
//            if (lockMapper.countLock(lockName) == 0) {
//                // 尝试获取锁
//                System.out.println("数据没有被锁定尝试获取锁");
//               int i= lockMapper.acquireLock(lockName);
//               if (i>0) {
//                   return true;
//               }
//            }
//
//                Thread.sleep(500); // 适当休眠，避免过于频繁的查询
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }
//
//        System.out.println("超时未能获取锁");
//        return false; // 超时，未能获取锁
//    }
//
//    @Transactional
//    public void releaseLock(String lockName) {
//        lockMapper.releaseLock(lockName);
//    }
//}