package com.crawler.backend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crawler.backend.mappers.TeacherListMapper;
import com.crawler.backend.model.TeacherList;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@AllArgsConstructor
public class TeacherListService extends ServiceImpl<TeacherListMapper, TeacherList> {
    @Resource
    TeacherListMapper teacherListMapper;
    /**
     * 添加老师关注
     * @param teacher
     * @return
     */
    public boolean addTeacher(TeacherList teacher){
        return save(teacher);
    }

    /**
     * 删除老师关注
     * @param tid
     * @param uid
     * @return
     */
    public boolean deleteTeacher(long tid,String uid){
        QueryWrapper<TeacherList> queryWrapper = new QueryWrapper();
        queryWrapper.eq("tid",tid);
        queryWrapper.eq("uid",uid);
        return remove(queryWrapper);
    }

    /**
     * 检查是否已经关注该老师
     * @param tid
     * @param uid
     * @return
     */
    public boolean checkTeacher(long tid,String uid) {
        QueryWrapper<TeacherList> queryWrapper = new QueryWrapper();
        queryWrapper.eq("tid", tid);
        queryWrapper.eq("uid", uid);
        return getOne(queryWrapper)!=null;
    }
    /**
     * 返回老师关注列表
     * @param uid
     * @return
     */
    public List<TeacherList> getTeacher(String uid){
        QueryWrapper<TeacherList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        return teacherListMapper.selectList(queryWrapper);
    }
}
