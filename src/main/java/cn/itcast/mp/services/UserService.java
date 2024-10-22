package cn.itcast.mp.services;

import cn.itcast.mp.pojo.User;

import java.util.List;

public interface UserService {
    public void batchInsertUsers(List<User> userList);
}
