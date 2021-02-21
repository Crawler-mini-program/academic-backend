package com.crawler.backend;

import com.crawler.backend.model.UserInfo;
import com.crawler.backend.service.AdminService;
import com.crawler.backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    //测试用户获取
    @Test
    public void printUser(){
        System.out.println(userService.allUser());
    }
    //测试用户添加
    @Test
    public void addUser(){
        UserInfo userInfo = new UserInfo(null,"123","深圳",1,"wjl","广东","中国");
        System.out.println(adminService.addUser(userInfo));
    }

}
