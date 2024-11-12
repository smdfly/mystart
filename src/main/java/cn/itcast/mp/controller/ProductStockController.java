package cn.itcast.mp.controller;


import cn.itcast.mp.pojo.User;
import cn.itcast.mp.services.UserService;
import cn.itcast.mp.services.impl.UserServicedd;
import cn.itcast.mp.tasklock.ScheduledTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 产品库存表
 * (ProductStock)表控制层
 *
 * @author 散装java
 */
@RestController
public class ProductStockController {
    @Resource
    private ScheduledTask scheduledTask;

    @Autowired
    private UserServicedd userService;

    /**
     * 模拟减库存操作 - MySQL 乐观锁 实现
     *
     * @return str
     */
    @GetMapping("/reduceStock/{id}")
    public String reduceStockOptimism(@PathVariable("id") Integer id) throws InterruptedException {
        return scheduledTask.executeTask();
    }

    @GetMapping("/userlist")
    public String user()  {

        List<User> list=new ArrayList<>();
        for (int i = 0; i < 100000; i++) {

            User user=new User();
            user.setId((long) i);
            user.setUserName("fdfas"+i);
            list.add(user);
        }

    //    return userService.Tret(list );
        return userService.batchInsertUsers(list );
    }

    /**
     * 模拟减库存操作 - MySQL 悲观锁 实现
     *
     * @return str
//     */
//    @GetMapping("/reduceStockGloomy/{id}")
//    public String reduceStockGloomy(@PathVariable("id") Integer id) {
//        return productStockService.reduceStockGloomy(id);
//    }
}

