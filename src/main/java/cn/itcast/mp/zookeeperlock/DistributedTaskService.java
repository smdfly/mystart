package cn.itcast.mp.zookeeperlock;
import cn.itcast.mp.tasklock.LockMapper;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DistributedTaskService {

    @Autowired
    private CuratorFramework curatorFramework;
    @Autowired
    private  LockMapper lockMapper;

  //  @Scheduled(cron = "0,5,10,15,20,25,30,35,40,45,50,55 * * * * *?")
    public void executeTask() {
        try {
            String lockPath = "/myDistributedLock"; // 用于加锁的节点

            // 获取分布式锁
            DistributedLock lock = new DistributedLock(curatorFramework, lockPath);
            if (lock.tryLock()) {
                try {
                    // 执行任务逻辑
                    System.out.println("插入用户信息");
                    lockMapper.addUser("张三","123456",lock.GetProcessId());
                    Thread.sleep(3000);
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("当前有其他节点在执行任务");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}