package cn.itcast.mp.services.impl;

import cn.itcast.mp.mapper.UserMapper;
import cn.itcast.mp.pojo.User;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServicedd extends ServiceImpl<UserMapper, User> {




    public String batchInsertUsers(List<User> userList) {
        // 每次插入的记录数，可以根据数据库的限制调整，默认是 50
        int batchSize = 100;
      boolean succ=  saveBatch(userList, batchSize);
       int rs=succ?1:0;
        return String.valueOf(rs);
    }
}