package cn.itcast.mp.mysqllock;

import cn.itcast.mp.tasklock.LockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTaskMysql {

    private final MysqlDistributedLock mysqlDistributedLock;

    @Autowired
    public ScheduledTaskMysql(MysqlDistributedLock mysqlDistributedLock) {
        this.mysqlDistributedLock = mysqlDistributedLock;
    }

  private LockMapper lockMapper;

  //  @Scheduled(cron = "0,5,10,15,20,25,30,35,40,45,50,55 * * * * *?")
    public void executeTask() throws InterruptedException {
        String lockName = "yourLockName";
        long timeoutSeconds = 60;
        String lockValue = null;

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        int milliseconds = currentDateTime.getNano(); // 纳秒转为毫秒
        System.out.println("时间=="+formattedDateTime+" " +milliseconds);

        try {
            lockValue = mysqlDistributedLock.tryLock(lockName, timeoutSeconds);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("未能获取锁，插入失败，跳过本次定时任务执行");
        }
        catch (DuplicateKeyException rr){
            System.out.println("未能获取锁，插入失败，跳过本次定时任务执行");
        }
        if (lockValue!= null) {
            try {
                // 执行定时任务的逻辑
                System.out.println("插入用户信息");
                lockMapper.addUser("张三","123456",mysqlDistributedLock.GetProcessId());
                Thread.sleep(3000);
            } finally {
                mysqlDistributedLock.releaseLock(lockName, lockValue);
            }
        } else {
            System.out.println("未能获取锁，跳过本次定时任务执行");
        }
    }
}