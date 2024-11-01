package cn.itcast.mp.services.impl;

import cn.itcast.mp.mapper.UserMapper;
import cn.itcast.mp.pojo.User;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class UserServicedd extends ServiceImpl<UserMapper, User> {




    public String batchInsertUsers(List<User> userList) {
        // 每次插入的记录数，可以根据数据库的限制调整，默认是 50
        int batchSize = 5000;
       boolean rs= saveBatch(userList, batchSize);
       int r=rs?1:0;
        System.out.println("插入结果"+r);
        return String.valueOf(r);
    }

    public  String Tret(List<User> userList) {
     long start=   System.currentTimeMillis();
        final int BATCH_SIZE = 5000; //
        // 每个批次大小 @Transactional public void insertData(List<DataEntity> dataList) {
        int totalSize = userList.size();
     int cpucore=     Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(cpucore); // 创建一个固定大小的线程池 for (int i =0; i < totalSize; i += BATCH_SIZE) {
        for (int i = 0; i < totalSize; i += BATCH_SIZE) {

            int end = Math.min(i + BATCH_SIZE, totalSize);
            List<User> chunk = userList.subList(i, end);

            int finalI = i;
            executorService.submit(() -> {
                System.out.println("线程启动---------"+Thread.currentThread().getName());
                System.out.println("批量插入"+ finalI +"到"+end+"条的数据");
                batchInsertUsers(chunk);
            })
            ;
        }
        long stop=System.currentTimeMillis();
        long ss=stop-start;
        System.out.println("运行时间="+ss);
        return String.valueOf(ss);
    }
}