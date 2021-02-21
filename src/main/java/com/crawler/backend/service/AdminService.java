package com.crawler.backend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crawler.backend.mappers.UserInfoMapper;
import com.crawler.backend.model.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService extends ServiceImpl<UserInfoMapper, UserInfo> {

    /**
     * 添加新用户
     * @param userInfo
     * @return
     */
    public boolean addUser(UserInfo userInfo){
        return save(userInfo);
    }
}
