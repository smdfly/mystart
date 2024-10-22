package cn.itcast.mp.tasklock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class ScheduledTask {

    @Autowired
    private LockServer2 lockServer2;
    @Autowired
    TastoderImpl tastoder;

  //  @Scheduled(cron = "20 * * * * ?")
    public String executeTask() throws InterruptedException {
        boolean hasLock = false;

            // 尝试获取锁，最多等待 5 秒
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        int milliseconds = currentDateTime.getNano(); // 纳秒转为毫秒
        System.out.println("时间=="+formattedDateTime+" " +milliseconds);

            hasLock = lockServer2.equrieLock(1);
            if (hasLock) {
                // 执行定时任务
           //     System.out.println("成功获取锁"+"Executing task by: " + Thread.currentThread().getName());
                // 模拟任务执行
                tastoder.excute();
                System.out.println("执行任务");


            } else {
                System.out.println("没有获取锁");
                System.out.println("Couldn't acquire lock, task not executed by: " + Thread.currentThread().getName());
            }
        return formattedDateTime;
    }
    }
