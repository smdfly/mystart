package cn.itcast.mp.tasklock;

import org.springframework.stereotype.Service;

@Service
public class TastoderImpl {

    public void excute() {
        System.out.println("开始处理定时任务");
    }
}
