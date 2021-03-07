package com.crawler.backend.controller;

import net.sf.json.JSONArray;
import com.crawler.backend.service.TeacherListService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@AllArgsConstructor
public class TeacherList {
    private TeacherListService teacherListService;
    @GetMapping("/get_teacher_list")
    @ApiOperation("获取关注的老师列表")
    public JSONArray get_teachers(HttpServletRequest request){
        String uid = request.getAttribute("openId").toString();
        List<com.crawler.backend.model.TeacherList> teachers = teacherListService.getTeacher(uid);
        JSONArray json = JSONArray.fromObject(teachers);
        return json;
    }
}
