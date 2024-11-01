//package cn.itcast.mp.redislock;
//import cn.itcast.mp.tasklock.LockMapper;
//import cn.itcast.mp.zookeeperlock.DistributedLock;
//import org.apache.curator.framework.CuratorFramework;
//import org.redisson.api.RedissonClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.UUID;
//
//@Service
//public class DistributedTaskServiceRedis {
//
//    @Resource
//    RedissonClient redissonClient;
//    @Resource
//    BulkRedisLock bulkRedisLock;
//    private  LockMapper lockMapper;
//
//    @Scheduled(cron = "0,5,10,15,20,25,30,35,40,45,50,55 * * * * *?")
//    public void executeTask() {
//
//
//            // 获取分布式锁
//
//            String requestId = UUID.randomUUID().toString().replace("-", "");
//            int expireTime = 10;
//            bulkRedisLock.lock(requestId, expireTime);
//
//            boolean lock = bulkRedisLock.lock(requestId,expireTime);
//
//            Thread watchDog = bulkRedisLock.watchDog(expireTime, requestId);
//            watchDog.setDaemon(true);
//            watchDog.start();
//            try {
//                    // 执行任务逻辑
//                    System.out.println("插入用户信息");
//                    lockMapper.addUser("张三","123456",bulkRedisLock.GetProcessId());
//                    Thread.sleep(3000);
//                } catch (InterruptedException ex) {
//                throw new RuntimeException(ex);
//            } finally {
//                bulkRedisLock.unlock(requestId);
//                }
//
//
//    }
//}