package com.crawler.backend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crawler.backend.mappers.UserInfoMapper;
import com.crawler.backend.model.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService extends ServiceImpl<UserInfoMapper, UserInfo> {
    /**
     * 测试数据库调用是否正常
     * @return
     */
    public List<UserInfo> allUser(){
        // AdminService继承了ServiceImpl之后增加了许多数据操作的方法
        // 可直接使用，文档见：https://mp.baomidou.com/guide/crud-interface.html
        return list();
    }
}
