package com.crawler.backend.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crawler.backend.mappers.UserInfoMapper;
import com.crawler.backend.model.UserInfo;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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

    /**
     * 根据openid获取用户信息
     * @param openid
     * @return
     */
    public UserInfo getUserById(String openid) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        System.out.println(openid);
        wrapper.eq("id", openid);
        return getOne(wrapper);
    }

    public boolean saveOrUpdateUser(JSONObject userInfoJSON) {
        String id=userInfoJSON.getString("openId");
        String city=userInfoJSON.getString("city");
        String country=userInfoJSON.getString("country");
        String province=userInfoJSON.getString("province");
        String name=userInfoJSON.getString("nickName");
        int gender=Integer.parseInt(userInfoJSON.getString("gender"));
        String avatar=userInfoJSON.getString("avatarUrl");
        boolean status=false;
        if(getUserById(id)==null)
            status = saveOrUpdate(new UserInfo(id,avatar,city,gender,name,province,country,0,null,0,null));
        else{
            UserInfo user = getUserById(id);
            long fieldid = user.getFieldid();
            String fieldname = user.getFieldname();
            long orgid = user.getOrgid();
            String orgname = user.getOrgname();
            status = saveOrUpdate(new UserInfo(id,avatar,city,gender,name,province,country,
                    fieldid,fieldname,orgid,orgname));
        }
        return status;
    }

    public JSONObject getUserByIdToJson(String openId,String token) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<UserInfo>();
        wrapper.eq("id", openId);
        UserInfo user = getOne(wrapper);
        JSONObject res = new JSONObject();
        res.put("id",user.getId());
        res.put("city",user.getCity());
        res.put("country",user.getCountry());
        res.put("province",user.getProvince());
        res.put("gender",user.getGender());
        res.put("avatar",user.getAvatar());
        res.put("name",user.getName());
        res.put("token",token);
        res.put("fieldName",user.getFieldname());
        res.put("orgName",user.getOrgname());
        return res;
    }
}
