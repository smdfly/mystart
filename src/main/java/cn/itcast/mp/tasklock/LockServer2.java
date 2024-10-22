package cn.itcast.mp.tasklock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;


@Service
public class LockServer2 {

    @Resource
    LockMapper lockMapper;

    @Autowired
    TastoderImpl tastoder;

    /**
     * 基于 MySQL 实现分布式锁（悲观锁）
     * 悲观锁 ：
     * 查询的时候，不管有没有线程竞争，都在 MySQL层面就加上了锁 ，
     * 所以，整个操作，必须要在一个事务中才行，这里通过 @Transactional
     *
     * @param id id
     * @return String
     */
    public Boolean equrieLock(Integer id) {
        // 加锁查询
        Map map = lockMapper.selectForID(id);
        System.out.println(map);
        if (map != null ) {

            try {
                int   i=lockMapper.updateID((Integer) map.get("id"),(Integer)map.get("version"));
                if(i>0){
                    System.out.println("获取到锁");
                    return true;
                }else {
                    System.out.println("没有获取到锁");
                    return false;
                }
            }catch ( RuntimeException re){
                re.printStackTrace();
                System.out.println("更新失败没有获取到sun");
                return false;
            }


        } else {
            System.out.println("请检查原始数据，没有获得锁");
            return false;
        }

    }

}
