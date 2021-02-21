package com.crawler.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class UserInfo {
    /**
     * 主键
     */
    private Long uid;
    /**
     * 微信用户头像
     */
    private String avatar;
    /**
     * 用户所在城市
     */
    private String city;
    /**
     * 性别，1是男，0是女
     */
    private Integer gender;
    /**
     * 用户名
     */
    private String name;
    /**
     * 用户所在省份
     */
    private String province;
    /**
     * 用户所在国家
     */
    private String country;
}
