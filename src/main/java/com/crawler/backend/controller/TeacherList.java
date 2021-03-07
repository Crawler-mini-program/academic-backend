package com.crawler.backend.controller;

import net.sf.json.JSONArray;
import com.crawler.backend.service.TeacherListService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@AllArgsConstructor
public class TeacherList {
    private TeacherListService teacherListService;
    @GetMapping("/get-teacher-list")
    @ApiOperation("获取关注的老师列表")
    public JSONObject get_teachers(HttpServletRequest request){
        //先注释掉，使用测试中的uid
        String uid = request.getAttribute("openId").toString();
        List<com.crawler.backend.model.TeacherList> teachers = teacherListService.getTeacher(uid);
        JSONArray json = JSONArray.fromObject(teachers);
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("list",json);
        jsonObject.accumulate("msg","获取列表成功");
        return jsonObject;
    }
    @GetMapping("/follow-teacher")
    @ApiOperation("关注老师")
    public JSONObject follow_teacher(HttpServletRequest request, String name, String school, long tid){
        String uid = request.getAttribute("openId").toString();
        com.crawler.backend.model.TeacherList teacherList = new com.crawler.backend.model.TeacherList(name, school, uid, tid);
        teacherListService.addTeacher(teacherList);
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("code", "0");
        jsonObject.accumulate("msg", "关注成功");
        return jsonObject;
    }
    @GetMapping("/defollow-teacher")
    @ApiOperation("取消关注老师")
    public JSONObject defollow_teacher(HttpServletRequest request, long tid){
        String uid = request.getAttribute("openId").toString();
//        com.crawler.backend.model.TeacherList teacherList = new com.crawler.backend.model.TeacherList(name, school, "oVhiP5cyPkaR-Gc2FG7e76WCEdrI", tid);
        JSONObject jsonObject = new JSONObject();
        if(teacherListService.deleteTeacher(tid, uid)){
            jsonObject.accumulate("code", "0");
            jsonObject.accumulate("msg", "取消关注成功");

        }
        else{
            jsonObject.accumulate("code", "1");
            jsonObject.accumulate("msg", "该tid不存在");
        }
        return jsonObject;
    }
}
