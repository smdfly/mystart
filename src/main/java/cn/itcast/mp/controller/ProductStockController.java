package cn.itcast.mp.controller;


import cn.itcast.mp.tasklock.ScheduledTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    /**
     * 模拟减库存操作 - MySQL 乐观锁 实现
     *
     * @return str
     */
    @GetMapping("/reduceStock/{id}")
    public String reduceStockOptimism(@PathVariable("id") Integer id) throws InterruptedException {
        return scheduledTask.executeTask();
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

