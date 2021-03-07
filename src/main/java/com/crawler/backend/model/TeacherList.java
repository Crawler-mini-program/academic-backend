package com.crawler.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class TeacherList {
    //老师名字
    private String name;
    //老师所在学校
    private String school;
    //用户的openid
    private String uid;
    //老师的id
    private long tid;
}
