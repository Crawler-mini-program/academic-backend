package com.crawler.backend.controller;


import com.crawler.backend.model.TeacherList;
import com.crawler.backend.model.UserInfo;
import com.crawler.backend.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.sf.json.JSONArray;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@AllArgsConstructor
public class ChangeUserInfoController {
    private UserService userService = new UserService();
    @GetMapping("/change-org")
    @ApiOperation("修改机构")
    public JSONObject change_org(HttpServletRequest request, long orgid, String orgname){
        //String uid = request.getAttribute("openid").toString();
        //测试用
        String uid = "oVhiP5cyPkaR-Gc2FG7e76WCEdrI";
        UserInfo userInfo = userService.getUserById(uid);
        JSONObject res = new JSONObject();
        if(userInfo != null){
            userInfo.setOrgid(orgid);
            userInfo.setOrgname(orgname);
            userService.saveOrUpdate(userInfo);
            res.put("code", 0);
            res.put("msg", "修改成功");
        }
        else{
            res.put("code", 1);
            res.put("msg", "该用户不存在");
        }
        return res;
    }
    @GetMapping("/change-field")
    @ApiOperation("修改感兴趣的领域")
    public JSONObject change_field(HttpServletRequest request, long fieldid, String fieldname){
        //String uid = request.getAttribute("openid").toString();
        //测试用
        String uid = "oVhiP5cyPkaR-Gc2FG7e76WCEdrI";
        UserInfo userInfo = userService.getUserById(uid);
        JSONObject res = new JSONObject();
        if(userInfo != null){
            userInfo.setFieldid(fieldid);
            userInfo.setFieldname(fieldname);
            userService.saveOrUpdate(userInfo);
            res.put("code", 0);
            res.put("msg", "修改成功");
        }
        else{
            res.put("code", 1);
            res.put("msg", "该用户不存在");
        }
        return res;
    }
    @GetMapping("/get-all-info")
    @ApiOperation("得到用户的所有信息")
    public JSONObject get_all_info(HttpServletRequest request){
        //String uid = request.getAttribute("openid").toString();
        String uid = "oVhiP5cyPkaR-Gc2FG7e76WCEdrI";
        UserInfo user = userService.getUserById(uid);
        JSONObject res = new JSONObject();
        if(user != null){
            res.put("id",user.getId());
            res.put("city",user.getCity());
            res.put("country",user.getCountry());
            res.put("province",user.getProvince());
            res.put("gender",user.getGender());
            res.put("avatar",user.getAvatar());
            res.put("name",user.getName());
            res.put("fieldName",user.getFieldname());
            res.put("orgName",user.getOrgname());
        }
        else{
            res.put("code", 1);
            res.put("msg", "该用户不存在");
        }
        return res;
    }
}
