package cn.itcast.mp.controller;

import cn.itcast.mp.pojo.User;
import cn.itcast.mp.services.impl.UserServicedd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserAddController {
	@Autowired
	UserServicedd userServicedd;

	@GetMapping("/mysql/")
	public String userAdd()  {

		List<User> list =new ArrayList<>();

		for (int i = 0; i < 1000000; i++) {
			User user=new User();
			user.setId((long) i);
			user.setUserName("dffsa"+i);
			list.add(user);

		}

		return userServicedd.batchInsertUsers(list);
	}

}
