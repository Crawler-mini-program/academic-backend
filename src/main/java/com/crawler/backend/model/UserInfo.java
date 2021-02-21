package com.crawler.backend.model;

import lombok.Data;

@Data
public class UserInfo {
    /**
     * 微信用户头像
     */
    private String avatarUrl;
    /**
     * 用户所在城市
     */
    private String city;
    /**
     * 性别，1是男，0是女
     */
    private int gender;
    /**
     * 用户名
     */
    private String nickName;
    /**
     * 用户所在省份
     */
    private String province;
    /**
     * 用户所在国家
     */
    private String country;
}
