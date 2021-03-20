package com.crawler.backend;

import com.crawler.backend.model.TeacherList;
import com.crawler.backend.model.UserInfo;
import com.crawler.backend.service.AdminService;
import com.crawler.backend.service.TeacherListService;
import com.crawler.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class BackendApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherListService teacherListService;
    //测试用户获取
    @Test
    public void printUser(){
        System.out.println(userService.allUser());
    }
    //测试用户添加
    @Test
    public void addUser(){
        UserInfo userInfo = new UserInfo(null,"123","深圳",1,"wjl","广东","中国",0,null,0,null);
        System.out.println(adminService.addUser(userInfo));
    }
    @Test
    public void addTeacher(){
        TeacherList teacherList=new TeacherList("王靖龙","北航","oVhiP5cyPkaR-Gc2FG7e76WCEdrI",1);
        if(teacherListService.save(teacherList)){
            log.info("添加成功");
        }
    }
    @Test
    public void deleteTeacher(){
        if(teacherListService.deleteTeacher(2,"oVhiP5cyPkaR-Gc2FG7e76WCEdrI")){
            log.info("删除成功");
        }
    }
    @Test
    public void selectTeacher(){
        System.out.println(teacherListService.getTeacher("oVhiP5cyPkaR-Gc2FG7e76WCEdrI"));
    }
}
