package com.crawler.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class UserInfo {
    /**
     * 主键openid
     */
    private String id;
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
    /**
     * 用户感兴趣的领域id
     */
    private long fieldid;
    /**
     * 用户感兴趣的领域名
     */
    private String fieldname;
    /**
     * 用户所在的机构id
     */
    private long orgid;
    /**
     * 用户所在的机构名
     */
    private String orgname;
}

